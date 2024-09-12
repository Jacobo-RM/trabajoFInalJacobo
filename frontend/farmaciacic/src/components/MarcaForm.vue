<template>
  <div class="addForm">
    <form @submit.prevent="submitForm">
      <h2>Agregar Marca</h2>
      <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="marca.nombre" required />
      </div>
      <div>
        <label for="paisOrigen">País de Origen:</label>
        <input type="text" id="paisOrigen" v-model="marca.paisOrigen" required />
      </div>
      <div>
        <label for="fechaFundacion">Fecha de Fundación:</label>
        <input type="date" id="fechaFundacion" v-model="marca.fechaFundacion" required />
      </div>
      <div>
        <label for="sitioWeb">Sitio Web:</label>
        <input type="url" id="sitioWeb" v-model="marca.sitioWeb" required />
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" v-model="marca.descripcion" required></textarea>
      </div>
      <div>
        <label for="imagenMarca">Seleccionar Imagen:</label>
        <input type="file" id="imagenMarca" @change="onFileChange">
      </div>

      <div class="button-group">
        <button type="submit">Guardar Marca</button>
        <button type="button" @click="goToMarcas">Volver</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useToast } from 'vue-toast-notification';

const marca = ref({
  nombre: '',
  paisOrigen: '',
  fechaFundacion: '',
  sitioWeb: '',
  descripcion: '',
  imagenMarca: null,
});

const router = useRouter();
const toast = useToast();

function onFileChange(event) {
  const file = event.target.files[0];
  if (file) {
    const fileName = file.name.split('.')[0];
    marca.value.imagenMarca = `/images/${fileName}.png`;
  }
}
    
const fetchMarcas = async (id) => {
  try {
    const response = await axios.get(`/api/farmacia/marcas/${id}`);
    marca.value = response.data;
  } catch (error) {
    console.error("Error fetching marca:", error);
  }
};

const submitForm = async () => {
  try {
    await axios.post('/api/farmacia/marcas/save', marca.value);
    toast.success('Marca agregada exitosamente');
    goToMarcas();
  } catch (error) {
    console.error('Error saving marca:', error);
  }
};



const goToMarcas = () => {
  router.push("/marcas");
};

onMounted(() => {
  const id = router.currentRoute.value.params.id;
  if (id) {
    fetchMarcas(id);
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
textarea {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1em;
  background-color: #f9f9f9;
  transition: border-color 0.3s;
}

input:focus,
textarea:focus {
  border-color: #4caf50;
  outline: none;
}

.button-group {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

input[type="number"] {
  text-align: right;
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