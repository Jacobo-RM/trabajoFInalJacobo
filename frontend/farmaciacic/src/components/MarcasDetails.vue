<template>
  <div>
    <router-link to="/marcas">
      <button class="volverAtras">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" color="#000000" fill="none">
          <path d="M3.99982 11.9998L19.9998 11.9998" stroke="currentColor" stroke-width="1.5" stroke-linecap="round"
            stroke-linejoin="round" />
          <path d="M8.99963 17C8.99963 17 3.99968 13.3176 3.99966 12C3.99965 10.6824 8.99966 7 8.99966 7"
            stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />
        </svg>
      </button>
    </router-link>
    <div v-if="marca">
      <div class="carta-general">
        <div class="marca-details">
          <div class="marca-imagen-container">
            <img :src="marca.imagenMarca" alt="Imagen de la marca" class="marca-imagen" />
          </div>
          <div class="marca-info">
            <h3>{{ marca.nombre }}</h3>
            <p><strong>Descripción:</strong> {{ marca.descripcion }}</p>
            <p><strong>País de Origen:</strong> {{ marca.paisOrigen }}</p>
            <p><strong>Fecha de Fundación:</strong> {{ marca.fechaFundacion }}</p>
            <p><strong>Sitio Web:</strong> <a :href="marca.sitioWeb" target="_blank">{{ marca.sitioWeb }}</a></p>
          </div>
        </div>
        <router-link :to="{ name: 'MedicamentoForm', query: { marca: JSON.stringify(marca) } }">
          <div class="botonCrear">
            <button class="botonCrear" title="Agregar Marca">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" color="#000000"
                fill="none">
                <circle cx="6.25" cy="6.25" r="4.25" stroke="currentColor" stroke-width="1.5" />
                <circle cx="17.75" cy="17.75" r="4.25" stroke="currentColor" stroke-width="1.5" />
                <circle cx="6.25" cy="17.75" r="4.25" stroke="currentColor" stroke-width="1.5" />
                <path d="M18 2V10M22 6L14 6" stroke="currentColor" stroke-width="1.5" stroke-linecap="round"
                  stroke-linejoin="round" />
              </svg>
            </button>
          </div>
        </router-link>
        <div v-if="medicamentos.length" class="cards-container">
          <div v-for="medicamento in medicamentos" :key="medicamento.id" class="card">
            <div class="card-body">
              <p class="card-title"><strong>{{ medicamento.nombre }}</strong></p>
              <p><strong>Precio:</strong> ${{ medicamento.precio.toFixed(2) }}</p>
              <p><strong>Descripción:</strong> {{ medicamento.descripcion }}</p>
              <p><strong>Stock:</strong> {{ medicamento.stock }}</p>
              <p v-if="medicamento.fecha_vencimiento"><strong>Fecha de Vencimiento:</strong> {{
                formatDate(medicamento.fecha_vencimiento) }}</p>
              <p><strong>Receta:</strong> {{ medicamento.receta ? 'Sí' : 'No' }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const marca = ref(null);
const medicamentos = ref([]);
const route = useRoute();

const fetchMarcaDetalles = async (id) => {
  try {
    const marcasResponse = await axios.get(`/api/farmacia/marcas/${id}`);
    marca.value = marcasResponse.data;

    const medicamentosResponse = await axios.get(`/api/farmacia/medicamentos/marca/${id}`);
    medicamentos.value = medicamentosResponse.data;
  } catch (error) {
    console.error('Error fetching marcas:', error);
  }
};

const formatDate = (dateString) => {
  const options = { year: "numeric", month: "long", day: "numeric" };
  return new Date(dateString).toLocaleDateString("es-ES", options);
};

onMounted(() => {
  const id = route.params.id;
  fetchMarcaDetalles(id);


});
</script>

<style scoped>
.carta-general {
  padding: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  border-radius: 10px;
  background-color: #fff;
  border: 1px solid #ddd;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 70%;
  margin: 30px auto;
  transition: transform 0.2s ease;
  overflow: hidden;
}

.carta-general:hover {
  transform: scale(1.02);
}

.marca-details {
  display: flex;
  gap: 20px;
  align-items: stretch;
}

.marca-imagen-container {
  width: 30%;
  display: flex;
  align-items: center;
}

.marca-imagen {
  width: 100%;
  height: 70%;
  object-fit: contain;
  border-radius: 8px;
  border: 1px solid #ddd;
}

.marca-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.marca-info h3 {
  font-size: 1.8rem;
  color: #333;
  margin-bottom: 10px;
}

.marca-info p {
  font-size: 0.95rem;
  color: #666;
  margin: 5px 0;
}

.cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
  margin-top: 20px;
}

.card {
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  padding: 15px;
  width: 280px;
  transition: box-shadow 0.2s ease;
}

.card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.card-body {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.card-title {
  font-size: 1.4rem;
  color: #333;
  margin-bottom: 10px;
}

.card-body p {
  margin: 0;
  font-size: 0.9rem;
  color: #666;
}

a {
  color: #007bff;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}

.botonCrear {
  display: flex;
  justify-content: end;
  margin-right: 3vw;
  margin-bottom: 1vh;
}

.volverAtras {
  position: absolute;
  top: 20px;
  left: 20px;
  background-color: #f9f9f9;
  border: none;
  border-radius: 50%;
  margin-top: 20vh;
  padding: 10px;
  cursor: pointer;
  transition: background-color 0.2s ease;
  border: 1px solid black;
}

.volverAtras:hover {
  background-color: #f0f0f0;
  border: 1px solid black;
}

.botonCrear {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 1vh;
  margin-left: auto;
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
</style>
