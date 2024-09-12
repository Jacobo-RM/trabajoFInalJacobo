<template>
  <div class="marca-form">
    <h2>Actualizar Marca</h2>
    <form @submit.prevent="updateMarca">
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" v-model="marca.nombre" maxlength="40" required />
      </div>
      <div class="form-group">
        <label for="paisOrigen">País de Origen:</label>
        <input type="text" v-model="marca.paisOrigen" disabled class="disabled-field"/>
      </div>
      <div class="form-group">
        <label for="fechaFundacion">Fecha de Fundación:</label>
        <input type="date" v-model="marca.fechaFundacion" disabled class="disabled-field"/>
      </div>
      <div class="form-group">
        <label for="sitioWeb">Sitio Web:</label>
        <input type="url" v-model="marca.sitioWeb" />
      </div>
      <div class="form-group">
        <label for="descripcion">Descripción:</label>
        <input type="text" v-model="marca.descripcion" maxlength="100" required />
      </div>
      <div class="form-group">
        <label for="imagenMarca">Imagen de la Marca:</label>
        <input type="file" id="imagenMarca" @change="onFileChange" />
      </div>
      <button type="submit">Guardar Cambios</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import { useToast } from "vue-toast-notification";

const marca = ref({
  id: "",
  nombre: "",
  paisOrigen: "",
  fechaFundacion: "",
  sitioWeb: "",
  descripcion: "",
  imagenMarca: "",
});

const route = useRoute();
const router = useRouter();
const toast = useToast();
const emit = defineEmits(['marcaActualizada']);

const fetchMarca = async () => {
  const id = route.params.id;
  try {
    const response = await axios.get(`/api/farmacia/marcas/${id}`);
    marca.value = response.data;
  } catch (error) {
    toast.error("Error al cargar la marca");
  }
};

function onFileChange(event) {
  const file = event.target.files[0];
  if (file) {
    const fileName = file.name.split('.')[0];
    marca.value.imagenMarca = `/images/${fileName}.png`;
  }
}

const updateMarca = async () => {
  try {
    await axios.put(`/api/farmacia/marcas/update/${marca.value.id}`, marca.value);
    toast.success("Marca actualizada exitosamente");
    router.push("/marcas");
  } catch (error) {
    toast.error("Error al actualizar la marca");
  }
};

onMounted(() => {
  fetchMarca();
});
</script>

<style scoped>
.marca-form {
  padding: 20px;
  max-width: 600px;
  margin: auto;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.marca-form h2 {
  margin-bottom: 20px;
  font-size: 1.5rem;
  color: #333;
}

.form-group {
  margin-bottom: 1rem;
}

.marca-form label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #333;
}

.marca-form input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
  color: #333;
}

.marca-form button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 0.75rem 1.25rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

.marca-form button:hover {
  background-color: #0056b3;
}

.disabled-field {
  background-color: #e0e0e0;
  color: #6c757d;
  cursor: not-allowed;
}
</style>
