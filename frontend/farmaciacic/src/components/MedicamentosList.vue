<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const medicamentos = ref([]);
const marcas = ref([]);

const fecthMarcas = async () => {
  try {
    const response = await axios.get('/api/farmacia/marcas');
    marcas.value = response.data;
    
  } catch (error) {
    console.error('Error al obtener las marcas:', error);
  }
};

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

onMounted(() => {
  fecthMarcas();
  fetchMedicamentos();
});
</script>

<template>
  <div class="medicamentos">
    <router-link :to="{ name: 'MedicamentoForm', params: { id: medicamentos.id } }">
      <div class="botonCrear">
        <button class="botonCrear"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"
            color="#000000" fill="none">
            <circle cx="6.25" cy="6.25" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <circle cx="17.75" cy="17.75" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <circle cx="6.25" cy="17.75" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <path d="M18 2V10M22 6L14 6" stroke="currentColor" stroke-width="1.5" stroke-linecap="round"
              stroke-linejoin="round" />
          </svg></button>
      </div>
</router-link>

    <div class="cards-container">
      <div class="card" v-for="medicamento in medicamentos" :key="medicamento.id">

        <div class="card-header">
          <h2 class="card-title">{{ medicamento.nombre }}</h2>
        </div>
        <div class="card-body">
          <p><strong>Precio:</strong> {{ medicamento.precio.toFixed(2) }}€</p>
          <p><strong>Descripción:</strong> {{ medicamento.descripcion }}</p>
          <p><strong>Stock:</strong> {{ medicamento.stock }}</p>
          <p v-if="medicamento.marca"><strong>Marca:</strong> {{ medicamento.marca.nombre }}</p>
          <p v-if="medicamento.fecha_vencimiento"><strong>Fecha de Vencimiento:</strong> {{ formatDate(medicamento.fecha_vencimiento) }}</p>
          <p><strong>Receta:</strong> {{ medicamento.receta ? 'Sí' : 'No' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>


<style scoped>

.medicamentos {
  padding: 20px;
  font-family: Arial, sans-serif;
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
  width: 300px;
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
  justify-content: end;
  margin-right: 3.5vw;
  margin-bottom: 3vh;
}

.botonCrear button {
  --bg: #e74c3c;
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
  box-shadow: #c0392b 0px 7px 2px, #000 0px 8px 5px;
}

.botonCrear button:hover {
  opacity: 1;
}

.botonCrear button:active {
  top: 4px;
  box-shadow: #c0392b 0px 3px 2px, #000 0px 3px 5px;
}
</style>
