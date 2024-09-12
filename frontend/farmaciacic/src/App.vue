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
  justify-content: space-between;
  background-color: #2b9348;
  padding: 10px 20px;
  color: #ffffff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  flex-wrap: wrap;
}

.logo {
  width: 50px;
  height: auto;
}

.nav {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 40px;
  flex-grow: 1;
}

.tituloYRutas {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}

.ruta {
  color: #ffffff;
  font-size: 1.2rem;
  font-weight: 500;
  text-decoration: none;
  padding: 8px 15px;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.ruta:hover {
  background-color: #1a6b2f;
}

.main-content {
  background-color: #f8f9fa;
  padding: 20px;
  min-height: calc(100vh - 70px);
}



@media (max-width: 480px) {
  .logo {
    display: none;
  }

  .ruta {
    font-size: 0.9rem;
    display: flex;
    align-items: center;
  }
}
</style>
