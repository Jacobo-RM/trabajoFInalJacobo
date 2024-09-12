<template>
  <div class="home-container">
    <section class="hero">
      <h1 class="hero-title">Bienvenidos a Farmacia CIC</h1>
      <p class="hero-subtitle">Tu salud, nuestra prioridad</p>
    </section>

    <section class="servicios">
      <h2 class="section-title">Nuestros Servicios</h2>
      <div class="servicios-grid">
        <div class="servicio-card">
          <router-link to="/marcas">
            <h3>Marcas</h3>
            <p>Consulta todas nuestras marcas</p>
          </router-link>
        </div>
        <div class="servicio-card">
          <router-link to="/medicamentos">
            <h3>Medicamentos</h3>
            <p>Todo nuestro stock de medicamentos</p>
          </router-link>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "Home",
  setup() {
    // Aquí puedes agregar cualquier lógica adicional si lo necesitas.
    return {};
  }
};
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow: hidden;
  background-image: url('../public/images/farmaciaBG.jpg');
  background-size: cover;
  background-position: center;
  border: 1px solid black;
}

.hero {
  text-align: center;
  padding: 40px 20px;
  background-color: #54ec5b;
  color: white;
  border-radius: 10px;
  margin-bottom: 40px;
  border: 1px solid #000000;
  border-radius: 10px;
  
}

.hero-logo {
  width: 120px;
  height: auto;
  margin-bottom: 20px;
}

.hero-title {
  font-size: 48px;
  margin-bottom: 10px;
}

.hero-subtitle {
  font-size: 24px;
  margin-bottom: 20px;
}

.hero-button {
  background-color: white;
  color: #54ec5b;
  padding: 10px 20px;
  text-decoration: none;
  font-size: 18px;
  border-radius: 5px;
}

.hero-button:hover {
  background-color: #f5f5f5;
  color: #54ec5b;
}

router-link {
  text-decoration: none;
  color: inherit;
}
.servicios {
  width: 100%;
  text-align: center;
  margin-bottom: 40px;
}

.section-title {
  font-size: 36px;
  margin-bottom: 20px;
}

.servicios-grid {
  display: flex;
  justify-content: center;
  gap: 20px;
  flex-wrap: wrap;
}

.servicio-card {
  background-color: #f0f0f0;
  padding: 20px;
  border-radius: 10px;
  border: 1px solid #000000;
  width: 300px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: background-color 0.3s;
}

.servicio-card:hover {
  background-color: #e0e0e0;

}

.servicio-card h3 {
  font-size: 24px;
  margin-bottom: 10px;
}

.servicio-card p {
  font-size: 16px;
  color: #666;
}
</style>