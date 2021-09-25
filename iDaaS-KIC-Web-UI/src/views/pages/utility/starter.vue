<script>
import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";

/**
 * Starter page component
 */
export default {
  page: {
    title: "Starter page",
    meta: [{ name: "description" }]
  },
  components: { Layout, PageHeader },
  data() {
    return {
      title: "Starter page",
      items: [
        {
          text: "Utility",
          href: "/"
        },
        {
          text: "Starter page",
          active: true
        }
      ]
    };
  }
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
  </Layout>
</template>