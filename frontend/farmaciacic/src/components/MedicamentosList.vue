<template>
  <div class="medicamentos">
    <!-- Buscador -->
    <div class="search-filter">
      <input type="text" v-model="searchTerm" placeholder="Buscar por nombre..." />
      <select v-model="filterBy" @change="applyFilter">
        <option value="">Todos</option>
        <option value="precioAsc">Precio: Menor a Mayor</option>
        <option value="precioDesc">Precio: Mayor a Menor</option>
        <option value="stockAsc">Stock: Menor a Mayor</option>
        <option value="stockDesc">Stock: Mayor a Menor</option>
      </select>
    </div>

    <!-- Botón para agregar medicamento -->
    <!-- <router-link :to="{ name: 'MedicamentoForm' }">
      <div class="botonCrear">
        <button class="botonCrear">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" color="#000000" fill="none">
            <circle cx="6.25" cy="6.25" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <circle cx="17.75" cy="17.75" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <circle cx="6.25" cy="17.75" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <path d="M18 2V10M22 6L14 6" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />
          </svg>
        </button>
      </div>
    </router-link> -->

    <!-- Contenedor de tarjetas por marca -->
    <div v-for="(meds, marca) in groupedMedicamentos" :key="marca" class="marca-group">
      <h2 class="marca-title">{{ marca }}</h2>
      <div class="cards-container">
        <div class="card" v-for="medicamento in filteredMedicamentos(marca)" :key="medicamento.id">
          <div class="card-header">
            <h2 class="card-title">{{ medicamento.nombre }}</h2>
          </div>
          <div class="card-body">
            <p><strong>Precio:</strong> {{ medicamento.precio.toFixed(2) }}€</p>
            <p><strong>Descripción:</strong> {{ medicamento.descripcion }}</p>
            <p><strong>Stock:</strong> {{ medicamento.stock }}</p>
            <p v-if="medicamento.fecha_vencimiento"><strong>Fecha de Vencimiento:</strong> {{ formatDate(medicamento.fecha_vencimiento) }}</p>
            <p><strong>Receta:</strong> {{ medicamento.receta ? 'Sí' : 'No' }}</p>
          </div>
          <div class="botonDelete">
            <button class="mostrarDetalles" title="Eliminar Medicamento" @click="showModal(medicamento.id)">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" color="#000000" fill="none">
                <path d="M19.5 5.5L18.8803 15.5251C18.7219 18.0864 18.6428 19.3671 18.0008 20.2879C17.6833 20.7431 17.2747 21.1273 16.8007 21.416C15.8421 22 14.559 22 11.9927 22C9.42312 22 8.1383 22 7.17905 21.4149C6.7048 21.1257 6.296 20.7408 5.97868 20.2848C5.33688 19.3626 5.25945 18.0801 5.10461 15.5152L4.5 5.5" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                <path d="M3 5.5H21M16.0557 5.5L15.3731 4.09173C14.9196 3.15626 14.6928 2.68852 14.3017 2.39681C14.215 2.3321 14.1231 2.27454 14.027 2.2247C13.5939 2 13.0741 2 12.0345 2C10.9688 2 10.436 2 9.99568 2.23412C9.8981 2.28601 9.80498 2.3459 9.71729 2.41317C9.32164 2.7167 9.10063 3.20155 8.65861 4.17126L8.05292 5.5" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                <path d="M9.5 16.5L9.5 10.5" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                <path d="M14.5 16.5L14.5 10.5" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
              </svg>
            </button>
          </div>
        </div>
      </div>
    </div>

    <div v-if="modalVisible" class="modal-overlay">
      <div class="modal-content">
        <button class="close-btn" @click="cancelDelete">×</button>
        <p>¿Estás seguro de que deseas eliminar este medicamento?</p>
        <button @click="deleteMedicamento(medicamentoIdBorrar)" class="modal-button">Sí, eliminar</button>
        <button @click="cancelDelete" class="modal-button">Cancelar</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';

const medicamentos = ref([]);
const searchTerm = ref('');
const filterBy = ref('');
const modalVisible = ref(false);
const medicamentoIdBorrar = ref(null);

const fetchMedicamentos = async () => {
  try {
    const response = await axios.get('/api/farmacia/medicamentos');
    medicamentos.value = response.data;
  } catch (error) {
    console.error('Error al obtener los medicamentos:', error);
  }
};

const formatDate = (dateString) => {
  const options = { year: "numeric", month: "long", day: "numeric" };
  return new Date(dateString).toLocaleDateString("es-ES", options);
};

// Agrupar medicamentos por marca
const groupedMedicamentos = computed(() => {
  return medicamentos.value.reduce((groups, medicamento) => {
    const marca = medicamento.marca ? medicamento.marca.nombre : 'Sin Marca';
    if (!groups[marca]) {
      groups[marca] = [];
    }
    groups[marca].push(medicamento);
    return groups;
  }, {});
});

// Filtrar medicamentos por marca
const filteredMedicamentos = (marca) => {
  let filtered = groupedMedicamentos.value[marca] || [];

  // Filtrar por término de búsqueda
  if (searchTerm.value) {
    filtered = filtered.filter(m => m.nombre.toLowerCase().includes(searchTerm.value.toLowerCase()));
  }

  // Aplicar filtro adicional
  switch (filterBy.value) {
    case 'precioAsc':
      filtered = filtered.sort((a, b) => a.precio - b.precio);
      break;
    case 'precioDesc':
      filtered = filtered.sort((a, b) => b.precio - a.precio);
      break;
    case 'stockAsc':
      filtered = filtered.sort((a, b) => a.stock - b.stock);
      break;
    case 'stockDesc':
      filtered = filtered.sort((a, b) => b.stock - a.stock);
      break;
  }

  return filtered;
};

// Función para eliminar un medicamento
const deleteMedicamento = async (id) => {
  try {
    await axios.delete(`/api/farmacia/medicamentos/delete/${id}`);
    medicamentos.value = medicamentos.value.filter((medicamento) => medicamento.id !== id);
    closeModal();
    // Mostrar un mensaje de éxito, asegúrate de que tienes configurado `toast` o una alternativa
  } catch (error) {
    // Mostrar un mensaje de error
  }
};

// Mostrar el modal de confirmación
const showModal = (id) => {
  medicamentoIdBorrar.value = id;
  modalVisible.value = true;
};

// Cerrar el modal
const closeModal = () => {
  modalVisible.value = false;
  medicamentoIdBorrar.value = null;
};

// Cancelar la eliminación
const cancelDelete = () => {
  closeModal();
};

onMounted(() => {
  fetchMedicamentos();
});
</script>

<style scoped>
.medicamentos {
  padding: 20px;
  font-family: Arial, sans-serif;
}



.search-filter {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-bottom: 20px;
  justify-content: center;
}

.search-filter input,
.search-filter select {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1em;
}

.marca-group {
  margin-bottom: 30px;
}

.marca-title {
  font-size: 2.5em;
  margin-bottom: 5vh;
  margin-top: 5vh;
  text-align: center;
}

.cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}

.card {
  border: 1px solid #ddd;
  border-radius: 8px;
  width: 100%;
  max-width: 300px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.card-header {
  background-color: #f4f4f9;
  padding: 15px;
  border-bottom: 1px solid #e0e0e0;
  font-size: 1.25em;
  font-weight: bold;
  color: #333;
}

.card-title {
  margin: 0;
  font-size: 1.5em;
}

.card-body {
  padding: 15px;
}

.card-body p {
  margin: 5px 0;
}

.card-body strong {
  font-weight: bold;
}

.botonCrear {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 2vh;
  margin-right: 1vw;
}

.botonCrear button {
  --bg: #308118;
  --text-color: #fff;
  position: relative;
  width: 40px;
  height: 40px;
  border: none;
  background: var(--bg);
  color: var(--text-color);
  padding: 10px;
  font-weight: bold;
  text-transform: uppercase;
  transition: 0.2s;
  border-radius: 5px;
  opacity: 0.8;
  letter-spacing: 1px;
  box-shadow: #000000 0px 7px 2px, #000 0px 8px 5px;
}

.botonCrear button:hover {
  opacity: 1;
}

.botonCrear button:active {
  top: 4px;
  box-shadow: #4bb82a 0px 3px 2px, #000 0px 3px 5px;
}

.botonDelete {
  display: flex;
  justify-content: end;
  margin-right: 3vw;
  margin-bottom: 1vh;
  margin: 2.4vh;
}

.botonDelete button {
  --bg: #ff604b;
  --text-color: #fff;
  position: relative;
  width: 40px;
  height: 40px;
  border: none;
  background: var(--bg);
  color: var(--text-color);
  padding: 7px;
  font-weight: bold;
  text-transform: uppercase;
  transition: 0.2s;
  border-radius: 5px;
  opacity: 0.8;
  letter-spacing: 1px;
  box-shadow: #fc2600 0px 7px 2px, #000 0px 8px 5px;
}

.botonDelete button:hover {
  opacity: 1;
}

.botonDelete button:active {
  top: 4px;
  box-shadow: #c0392b 0px 3px 2px, #000 0px 3px 5px;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 90%;
  max-width: 300px;
  text-align: center;
}

.close-btn {
  background: none;
  border: none;
  font-size: 20px;
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}

.modal-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 10px;
  border-radius: 5px;
  cursor: pointer;
}

.modal-button:hover {
  background-color: #0056b3;
}


@media (max-width: 480px) {
  .card-header {
    font-size: 1em;
  }

  .card-title {
    font-size: 1.2em;
  }
  .search-filter{
    gap: 5px;
    height: 20px;
    display: flex;
  }

  .search-filter select{
    display: none;
  }


  .modal-content {
    width: 90%;
    max-width: 90%;
  }
}
</style>
