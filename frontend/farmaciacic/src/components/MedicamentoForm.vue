<template>
  <div>
    <form @submit.prevent="saveMedicamento">
      <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="newMedicamento.nombre" required />
      </div>
      <div>
  <label for="precio">Precio:</label>
  <div class="price-container">
    <input type="number" id="precio" v-model="newMedicamento.precio" min="0" required />
    <span class="currency-symbol">€</span>
  </div>
</div>


      <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" v-model="newMedicamento.descripcion" required />
      </div>
      <div>
        <label for="stock">Stock:</label>
        <input type="number" id="stock" v-model="newMedicamento.stock" min = "0" required />
      </div>
      <div>
        <label for="marca">Marca:</label>
        <select id="marca" v-model="selectedMarca">
          <option v-for="marca in marcas" :key="marca.id" :value="marca">
            {{ marca.nombre }}
          </option>
        </select>
      </div>
      <div>
        <label for="fechaVencimiento">Fecha de Vencimiento:</label>
        <input type="date" id="fechaVencimiento" v-model="newMedicamento.fechaVencimiento" min="2024-09-13" required />
      </div>
      <div>
        <label for="necesitaReceta">Necesita Receta: 
          <input type="checkbox" id="necesitaReceta" v-model="newMedicamento.necesitaReceta" />
        </label>
      </div>
      <button type="submit">Añadir Medicamento</button>
      <button type="button" @click="goToMedicamentos">Volver</button>
    </form>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { useRouter, useRoute } from 'vue-router';
import { useToast } from 'vue-toast-notification';

const toast = useToast();
const router = useRouter();
const route = useRoute();

const newMedicamento = ref({
  id: null,
  nombre: "",
  precio: 0,
  descripcion: "",
  stock: 0,
  marca: null,
  fechaVencimiento: "",
  necesitaReceta: false,
});

const marcas = ref([]);
const selectedMarca = ref(null);

const fetchMarcas = async () => {
  try {
    const response = await axios.get("/api/farmacia/marcas");
    marcas.value = response.data;
    
    const marcaString = route.query.marca;
    if (marcaString) {
      const marcaObj = JSON.parse(marcaString);
      selectedMarca.value = marcaObj; // Selecciona la marca adecuada
      newMedicamento.value.marca = marcaObj; // Asigna el objeto de marca completo
    }
  } catch (error) {
    console.error("Error fetching marcas:", error);
  }
};

const fetchMedicamento = async (id) => {
  try {
    const response = await axios.get(`/api/farmacia/medicamentos/${id}`);
    Object.assign(newMedicamento.value, response.data);
  } catch (error) {
    console.error("Error fetching medicamento:", error);
  }
};

const saveMedicamento = async () => {
  try {
    await axios.post("/api/farmacia/medicamentos/save", newMedicamento.value);
    toast.success("Medicamento guardado con éxito");
    router.push("/medicamentos");
  } catch (error) {
    toast.error("Error guardando el medicamento");
    console.error("Error saving medicamento:", error);
  }
};

const goToMedicamentos = () => {
  router.push("/medicamentos");
};

onMounted(() => {
  fetchMarcas();

  const id = route.params.id;
  if (id) {
    fetchMedicamento(id);
  }
});
</script>

  
  <style scoped>
  .addForm {
    display: flex;
    justify-content: center;
    align-items: center;
    height: calc(100vh - 100px);
    background-color: #f9f9f9;
  }
  
  form {
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    max-width: 600px;
    padding: 40px;
    border: 1px solid #ddd;
    border-radius: 10px;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    gap: 20px;
  }
  
  form div {
    display: flex;
    flex-direction: column;
    width: 100%;
    gap: 10px;
  }
  
  label {
    font-weight: bold;
    color: #333;
  }
  
  input,
  textarea,
  select {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1em;
    background-color: #f9f9f9;
    transition: border-color 0.3s;
  }
  
  input:focus,
  textarea:focus,
  select:focus {
    border-color: #4caf50;
    outline: none;
  }
  
  input[type="number"] {
    text-align: right;
  }
  .price-container {
  display: flex;
  align-items: center;
  position: relative;
  width: 95%; 
  margin-left: 1vw;
}

.price-container input {
  width: 100%; /* Hace que el input ocupe todo el ancho disponible */
  padding-right: 2rem; /* Espacio para el símbolo del euro */
}

.currency-symbol {
  position: absolute;
  right: 0;
  padding: 0 0.5rem;
  color: #333;
  background-color: #f9f9f9;
  border-left: 1px solid #ccc;
  height: 100%;
  display: flex;
  align-items: center;
}


  .button-group {
    display: flex;
    justify-content: space-between;
    width: 100%;
  }
  
  button {
    background-color: #4caf50;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 5px;
    font-size: 1em;
    transition: background-color 0.3s;
  }
  
  button:hover {
    background-color: #45a049;
  }
  
  button[type="button"] {
    background-color: #f44336;
  }
  
  button[type="button"]:hover {
    background-color: #e53935;
  }
  </style>
  