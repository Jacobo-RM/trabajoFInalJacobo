<template>
  <div>
    <router-link to="/marcas">
      <button class="volverAtras"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" color="#000000" fill="none">
    <path d="M3.99982 11.9998L19.9998 11.9998" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />
    <path d="M8.99963 17C8.99963 17 3.99968 13.3176 3.99966 12C3.99965 10.6824 8.99966 7 8.99966 7" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />
</svg></button>
    </router-link>
    <div v-if="marca">
      <div class="carta-general">
        <div class="marca-details">
          <img :src="marca.imagenMarca" alt="Imagen de la marca" class="marca-imagen"/>
          <p><strong>Descripción:</strong> {{ marca.descripcion }}</p>
          <p><strong>País de Origen:</strong> {{ marca.paisOrigen }}</p>
          <p><strong>Fecha de Fundación:</strong> {{ marca.fechaFundacion }}</p>
          <p><strong>Sitio Web:</strong> <a :href="marca.sitioWeb">{{ marca.sitioWeb }}</a></p>
        </div>
        <router-link :to="{ name: 'MedicamentoForm', query: { marcaId: marca.id } }">
          <div class="botonCrear">
          <button class="botonCrear" title="Agregar Marca"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"
            color="#000000" fill="none">
            <circle cx="6.25" cy="6.25" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <circle cx="17.75" cy="17.75" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <circle cx="6.25" cy="17.75" r="4.25" stroke="currentColor" stroke-width="1.5" />
            <path d="M18 2V10M22 6L14 6" stroke="currentColor" stroke-width="1.5" stroke-linecap="round"
              stroke-linejoin="round" />
          </svg></button>
        </div>
        </router-link>
        <div v-if="medicamentos.length" class="cards-container">
          <div v-for="medicamento in medicamentos" :key="medicamento.id" class="card">
            <div class="card-body">
              <p class="card-title">
                <strong>{{ medicamento.nombre }}</strong>
              </p>
              <p><strong>Precio:</strong> ${{ medicamento.precio.toFixed(2) }}</p>
              <p><strong>Descripción:</strong> {{ medicamento.descripcion }}</p>
              <p><strong>Stock:</strong> {{ medicamento.stock }}</p>
              <p v-if="medicamento.fecha_vencimiento"><strong>Fecha de Vencimiento:</strong> {{ formatDate(medicamento.fecha_vencimiento) }}</p>
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
  width: 50%;
  margin: 0 auto;
  margin-top: 15vh;
  transition: transform 0.2s ease;
}

.carta-general:hover {
  transform: scale(1.02);
}

.marca-details {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
  text-align: center;
}

.marca-imagen {
  max-width: 20%;
  height: auto;
  margin-bottom: 20px;
  object-fit: cover;
}

.cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
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



.volverAtras {
  position: absolute;
  top: 20px;
  left: 20px;
  background-color: #f9f9f9;
  border: none;
  border-radius: 50%;
  padding: 10px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.volverAtras:hover {
  background-color: #a87b7b;
}
</style>
