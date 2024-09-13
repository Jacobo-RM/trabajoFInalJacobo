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
export default {
  name: "Home",
};
</script>

<style scoped>

.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-image: url('../public/images/farmaciaBG.jpg');
  background-size: cover;
  background-position: center;
  height: 100vh;
  width: auto;
  box-sizing: border-box;
}

.hero {
  text-align: center;
  padding: 60px 20px;
  color: #333;
  border-radius: 15px;
  margin-bottom: 40px;
  width: 90%;
  max-width: 800px;
  background-color: #ffffff;
  border: 1px solid #000000;
}

.hero-title {
  font-size: 2.5rem;
  margin-bottom: 10px;
  color: #2b9348;
  font-weight: bold;
}

.hero-subtitle {
  font-size: 1.25rem;
  margin-bottom: 20px;
  color: #555;
}

.servicios {
  width: 60%;
  max-width: 800px;
  text-align: center;
  margin-bottom: 40px;
  border: 1px solid #000000;
  border-radius: 15px;
  padding: 20px;
  background-color: white;
}

.section-title {
  font-size: 2rem;
  margin-bottom: 20px;
  color: #2b9348;
}

.servicios-grid {
  display: flex;
  justify-content: center;
  gap: 20px;
  flex-wrap: wrap;
}

.servicio-card {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 15px;
  border: 1px solid #e0e0e0;
  width: 280px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s, box-shadow 0.3s;
}

.servicio-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.servicio-card h3 {
  font-size: 1.4rem;
  margin-bottom: 10px;
  color: #2b9348;
}

.servicio-card p {
  font-size: 0.9rem;
  color: #777;
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 2rem;
  }

  .hero-subtitle {
    font-size: 1rem;
  }

  .section-title {
    font-size: 1.5rem;
  }

  .servicio-card {
    width: 90%;
  }

  .servicio-card h3 {
    font-size: 1.2rem;
  }

  .servicio-card p {
    font-size: 0.85rem;
  }
}

@media (max-width: 480px) {
  .hero-title {
    font-size: 1.5rem;
  }

  .hero-subtitle {
    font-size: 0.85rem;
  }

  .section-title {
    font-size: 1.25rem;
  }

  .servicio-card {
    padding: 15px;
    width: 100%;
  }

  .servicio-card h3 {
    font-size: 1rem;
  }

  .servicio-card p {
    font-size: 0.75rem;
  }
}
</style>
