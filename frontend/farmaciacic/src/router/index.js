import { createRouter, createWebHistory } from "vue-router";
import Home from "@/components/Home.vue";
import MedicamentosList from "@/components/MedicamentosList.vue";
import MarcasList from "@/components/MarcasList.vue";
import MarcasDetails from "@/components/MarcasDetails.vue";
import MarcaForm from "@/components/MarcaForm.vue";
import MedicamentoForm from "@/components/MedicamentoForm.vue";
import MarcaUpdate from "@/components/MarcaUpdate.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },

    {
      path: "/medicamentos",
      name: "Medicamentos",
      component: MedicamentosList,
    },

    {
      path: "/marcas",
      name: "Marcas",
      component: MarcasList,
    },

    {
      path: "/marca/:id",
      name: "MarcasDetails",
      component: MarcasDetails,
      props: true,

    },

    {
      path: "/marca-form",
      name: "MarcaForm",
      component: MarcaForm,

    },

    {
      path: "/medicamento-form",
      name: "MedicamentoForm",
      component: MedicamentoForm,
    },

    {
      path: '/marcas/update/:id',
      name: 'MarcaUpdate',
      component: MarcaUpdate,


    },
  ],
});

export default router;
