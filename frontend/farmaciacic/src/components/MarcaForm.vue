<template>
  <div class="addForm">
    <form @submit.prevent="submitForm">
      <h2>Agregar Marca</h2>
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="marca.nombre" required />
      </div>
      <div class="form-group">
        <label for="paisOrigen">País de Origen:</label>
        <input type="text" id="paisOrigen" v-model="marca.paisOrigen" required />
      </div>
      <div class="form-group">
        <label for="fechaFundacion">Fecha de Fundación:</label>
        <input type="date" id="fechaFundacion" v-model="marca.fechaFundacion" required />
      </div>
      <div class="form-group">
        <label for="sitioWeb">Sitio Web:</label>
        <input type="url" id="sitioWeb" v-model="marca.sitioWeb" required />
      </div>
      <div class="form-group">
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" v-model="marca.descripcion" required></textarea>
      </div>
      <div class="form-group">
        <label for="imagenMarca">Seleccionar Imagen:</label>
        <input type="file" id="imagenMarca" @change="onFileChange" />
      </div>

      <div class="button-group">
        <button type="submit" class="btn-primary">Guardar Marca</button>
        <button type="button" class="btn-secondary" @click="goToMarcas">Volver</button>
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
  height: 100vh;
  background: linear-gradient(to bottom right, #e6f7ff, #ffffff);
  padding: 20px;
}

form {
  width: 100%;
  max-width: 600px;
  padding: 40px;
  border-radius: 12px;
  background-color: #ffffff;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

form:hover {
  transform: scale(1.02);
}

h2 {
  margin-bottom: 20px;
  font-size: 1.8em;
  color: #333;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 1.5px;
}

.form-group {
  margin-bottom: 20px;
  position: relative;
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: 600;
  color: #555;
  font-size: 0.9em;
  text-transform: uppercase;
}

input,
textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1em;
  background-color: #fafafa;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

input:focus,
textarea:focus {
  border-color: #4caf50;
  outline: none;
  box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
}

input[type="file"] {
  display: none;
}

label[for="imagenMarca"] {
  cursor: pointer;
  display: inline-block;
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  border-radius: 4px;
  transition: background-color 0.3s ease;
  text-align: center;
  font-size: 0.9em;
}

label[for="imagenMarca"]:hover {
  background-color: #45a049;
}

.button-group {
  display: flex;
  justify-content: space-between;
}

button {
  padding: 12px 24px;
  font-size: 1em;
  border: none;
  cursor: pointer;
  border-radius: 4px;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.btn-primary {
  background-color: #4caf50;
  color: white;
}

.btn-primary:hover {
  background-color: #45a049;
  transform: translateY(-2px);
}

.btn-secondary {
  background-color: #f44336;
  color: white;
}

.btn-secondary:hover {
  background-color: #e53935;
  transform: translateY(-2px);
}



</style>
