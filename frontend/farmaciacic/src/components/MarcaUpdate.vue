<template>
  <div class="marca-form">
    <h2>Actualizar Marca</h2>
    <form @submit.prevent="updateMarca">
      <div>
        <label for="nombre">Nombre:</label>
        <input type="text" v-model="marca.nombre" maxlength="40" required />
      </div>
      <div>
        <label for="paisOrigen">País de Origen:</label>
        <input type="text" v-model="marca.paisOrigen" disabled class="disabled-field"/>
      </div>
      <div>
        <label for="fechaFundacion">Fecha de Fundación:</label>
        <input type="date" v-model="marca.fechaFundacion"  disabled class="disabled-field"/>
      </div>
      <div>
        <label for="sitioWeb">Sitio Web:</label>
        <input type="url" v-model="marca.sitioWeb" />
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" v-model="marca.descripcion" maxlength="100" required />
      </div>
      <div>
        <label for="imagenMarca">Imagen de la Marca:</label>
        <input type="text" v-model="marca.imagenMarca" />
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


// Obtener la marca por ID
const fetchMarca = async () => {
  const id = route.params.id;
  try {
    const response = await axios.get(`/api/farmacia/marcas/${id}`);
    marca.value = response.data;
  } catch (error) {
    toast.error("Error al cargar la marca");
  }
};

// Actualizar la marca
const updateMarca = async () => {
  try {
    await axios.put(`/api/farmacia/marcas/update/${marca.value.id}`, marca.value);
    toast.success("Marca actualizada exitosamente");
    router.push("/marcas");
  } catch (error) {
    toast.error("Error al actualizar la marca");
  }
};

// Cargar la marca al montar el componente
onMounted(() => {
  fetchMarca();
});
</script>

<style scoped>
/* Estilos personalizados para el formulario */
.marca-form {
  padding: 20px;
  max-width: 600px;
  margin: auto;
}

.marca-form div {
  margin-bottom: 10px;
}

.marca-form label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.marca-form input,
.marca-form textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.marca-form button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
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
