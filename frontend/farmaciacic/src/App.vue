<script>
import { ref, onMounted } from "vue";
import axios from "./axios";
import { RouterLink, RouterView } from "vue-router";

const marca = ref([]);

const fecthFarmacia = async () => {
  try {
    const response = await axios.get("/api/farmacia/");
    marca.value = response.data;
  } catch (error) {
    console.error("Error fetching farmacia:", error);
  }
};

onMounted(() => {
  fecthFarmacia();
});
</script>
<template>
  <header class="header">
    <img src="../public/images/farmaciaLogo.png" alt="Logo de la Farmacia" class="logo" />
    <nav class="nav">
      <div class="tituloYRutas">
        <RouterLink to="/" class="ruta">Inicio</RouterLink>
        <RouterLink to="/marcas" class="ruta">Marcas</RouterLink>
        <RouterLink to="/medicamentos" class="ruta">Medicamentos</RouterLink>
      </div>
    </nav>
    <img src="../public/images/farmaciaLogo.png" alt="Logo de la Farmacia" class="logo" />

  </header>

  <main class="main-content">
    <RouterView />
  </main>
</template>

<style scoped>
.header {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #31c456;
  border: 1px solid black;
  border-radius: 5px;
  color: #ffffff;
  height: 30px;
  padding: 10px;
}

.logo {
  width: 50px;
  height: auto;
  margin-right: 20px;
  margin-left: 20px;
}

.nav {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 40px;
}

.tituloYRutas {
  display: flex;
  gap: 30px;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.ruta {
  color: #ffffff;
  font-size: 22px;
  font-weight: 500;
  text-decoration: none;
  transition: color 0.3s, background-color 0.3s;
  padding: 8px 15px;
  border-radius: 5px;
}

.ruta:hover {
  background-color: #0e7928;
  color: #ffffff;
}

.router-link-active {
  font-weight: bold;
  text-decoration: underline;
}

.main-content {
  position: relative;
  background-color: #f4f4f9;
  height: 100vh;
  padding: 20px;

}
</style>
