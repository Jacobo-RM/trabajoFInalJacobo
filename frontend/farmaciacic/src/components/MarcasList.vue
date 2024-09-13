<template>

  <div class="marcas">
 
    <router-link :to="{ name: 'MarcaForm', params: { id: marcas.id } }">
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
    <div class="cards-container">
      <div class="card" v-for="marca in marcas" :key="marca.id">
        <div class="card-header">
          <img :src="marca.imagenMarca" alt="Imagen de la marca" class="marca-imagen" />
        </div>
        <div class="card-body">
          <p><strong>Descripción:</strong> {{ marca.descripcion }}</p>
          <p><strong>País de Origen:</strong> {{ marca.paisOrigen }}</p>
        </div>
        <div class="botones">
          <router-link :to="{ name: 'MarcasDetails', params: { id: marca.id } }">
            <div class="botonMostrarDetalles" title="Mostrar Detalles">
              <button class="mostrarDetalles"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24"
                  height="24" color="#000000" fill="none">
                  <path
                    d="M2.5 12C2.5 7.52166 2.5 5.28249 3.89124 3.89124C5.28249 2.5 7.52166 2.5 12 2.5C16.4783 2.5 18.7175 2.5 20.1088 3.89124C21.5 5.28249 21.5 7.52166 21.5 12C21.5 16.4783 21.5 18.7175 20.1088 20.1088C18.7175 21.5 16.4783 21.5 12 21.5C7.52166 21.5 5.28249 21.5 3.89124 20.1088C2.5 18.7175 2.5 16.4783 2.5 12Z"
                    stroke="currentColor" stroke-width="1.5" />
                  <path d="M11 7L17 7" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                  <path d="M7 7L8 7" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                  <path d="M7 12L8 12" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                  <path d="M7 17L8 17" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                  <path d="M11 12L17 12" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                  <path d="M11 17L17 17" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                </svg></button>
            </div>
          </router-link>
          <div class="botonUpdate">
            <router-link :to="{ name: 'MarcaUpdate', params: { id: marca.id } }">
              <button class="mostrarDetalles" title="Actualizar Marca"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24"
                  height="24" color="#000000" fill="none">
                  <path
                    d="M14.0737 3.88545C14.8189 3.07808 15.1915 2.6744 15.5874 2.43893C16.5427 1.87076 17.7191 1.85309 18.6904 2.39232C19.0929 2.6158 19.4769 3.00812 20.245 3.79276C21.0131 4.5774 21.3972 4.96972 21.6159 5.38093C22.1438 6.37312 22.1265 7.57479 21.5703 8.5507C21.3398 8.95516 20.9446 9.33578 20.1543 10.097L10.7506 19.1543C9.25288 20.5969 8.504 21.3182 7.56806 21.6837C6.63212 22.0493 5.6032 22.0224 3.54536 21.9686L3.26538 21.9613C2.63891 21.9449 2.32567 21.9367 2.14359 21.73C1.9615 21.5234 1.98636 21.2043 2.03608 20.5662L2.06308 20.2197C2.20301 18.4235 2.27297 17.5255 2.62371 16.7182C2.97444 15.9109 3.57944 15.2555 4.78943 13.9445L14.0737 3.88545Z"
                    stroke="currentColor" stroke-width="1.5" stroke-linejoin="round" />
                  <path d="M13 4L20 11" stroke="currentColor" stroke-width="1.5" stroke-linejoin="round" />
                  <path d="M14 22L22 22" stroke="currentColor" stroke-width="1.5" stroke-linecap="round"
                    stroke-linejoin="round" />
                </svg></button>
            </router-link>
          </div>

          <div class="botonDelete">
            <button class="mostrarDetalles" title="Eliminar Marca" @click="showModal(marca.id)"><svg xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24" width="24" height="24" color="#000000" fill="none">
                <path
                  d="M19.5 5.5L18.8803 15.5251C18.7219 18.0864 18.6428 19.3671 18.0008 20.2879C17.6833 20.7431 17.2747 21.1273 16.8007 21.416C15.8421 22 14.559 22 11.9927 22C9.42312 22 8.1383 22 7.17905 21.4149C6.7048 21.1257 6.296 20.7408 5.97868 20.2848C5.33688 19.3626 5.25945 18.0801 5.10461 15.5152L4.5 5.5"
                  stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                <path
                  d="M3 5.5H21M16.0557 5.5L15.3731 4.09173C14.9196 3.15626 14.6928 2.68852 14.3017 2.39681C14.215 2.3321 14.1231 2.27454 14.027 2.2247C13.5939 2 13.0741 2 12.0345 2C10.9688 2 10.436 2 9.99568 2.23412C9.8981 2.28601 9.80498 2.3459 9.71729 2.41317C9.32164 2.7167 9.10063 3.20155 8.65861 4.17126L8.05292 5.5"
                  stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                <path d="M9.5 16.5L9.5 10.5" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
                <path d="M14.5 16.5L14.5 10.5" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" />
              </svg></button>
          </div>


        </div>
      </div>
    </div>
    <div v-if="modalVisible" class="modal-overlay">
      <div class="modal-content">
        <button class="close-btn" @click="cancelDelete">×</button>
        <p>¿Estás seguro de que deseas eliminar esta marca?</p>
        <button @click="deleteMarca(marcaIdBorrar)" class="modal-button">
          Sí, eliminar
        </button>
        <button @click="cancelDelete" class="modal-button">Cancelar</button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { useToast } from "vue-toast-notification";

const marcas = ref([]);
const modalVisible = ref(false);
const marcaIdBorrar = ref(null);
const router = useRouter();
const toast = useToast();

const fetchMarcas = async () => {
  try {
    const response = await axios.get("/api/farmacia/marcas");
    marcas.value = response.data;
  } catch (error) {
    console.error("Error fetching marcas:", error);
  }
};


const deleteMarca = async (id) => {
  try {
    await axios.delete(`/api/farmacia/marcas/delete/${id}`);
    marcas.value = marcas.value.filter((marca) => marca.id !== id);
    closeModal();
    toast.success("Marca eliminada exitosamente");
  } catch (error) {
    toast.error("Error eliminando la marca");
  }
};

const showModal = (id) => {
  marcaIdBorrar.value = id;
  modalVisible.value = true;
};

const closeModal = () => {
  modalVisible.value = false;
  marcaIdBorrar.value = null;
};

const cancelDelete = () => {
  closeModal();
};

onMounted(() => {
  fetchMarcas();
});
</script>


<style scoped>



.marcas {
  padding: 20px;
  font-family: Arial, sans-serif;
}

.marca-imagen {
  width: auto;
  height: 15vh;
}

.cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
  margin-top: 20px;
}

.card:hover {
  transform: scale(1.02);
  background-color: #f4f4f4;
  border: 1px solid #000;
}

.card {
  border: 1px solid #ddd;
  border-radius: 8px;
  width: 20vw;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.card-header {
  background-color: #f4f4f4;
  padding: 10px;
  text-align: center;
}

.card-image {
  width: 100%;
  height: auto;
}

.card-title {
  margin: 0;
  font-size: 1.2em;
}

.card-body {
  padding: 10px;
  height: 10vh;

}

.botones {
  display: flex;
  justify-content: space-around;
  margin-top: 5vh;
}

.card-body p {
  margin: 5px 0;
}

.card-body strong {
  font-weight: bold;
}

.botonMostrarDetalles {
  padding: 20px;
}

.botonMostrarDetalles button {
  --bg: #96fff1;
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
  box-shadow: #1fbcc7 0px 7px 2px, #000 0px 8px 5px;
}

.botonMostrarDetalles button:hover {
  opacity: 1;
}

.botonMostrarDetalles button:active {
  top: 4px;
  box-shadow: #c0392b 0px 3px 2px, #000 0px 3px 5px;
}

.botonMostrarDetalles {
  padding: 20px;
}

.botonUpdate button {
  --bg: #f4ff5f;
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
  box-shadow: #cae91d 0px 7px 2px, #000 0px 8px 5px;
}

.botonUpdate button:hover {
  opacity: 1;
}

.botonUpdate button:active {
  top: 4px;
  box-shadow: #c0392b 0px 3px 2px, #000 0px 3px 5px;
}


.botonUpdate {
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


.botonDelete {
  display: flex;
  justify-content: end;
  margin-right: 3vw;
  margin-bottom: 1vh;
  margin: 2.4vh;
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
  width: 300px;
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
</style>