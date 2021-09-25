<script>
import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";
import appConfig from "@/app.config";
/**
 * Notification Component
 */
export default {
  page: {
    title: "Notification",
    meta: [{ name: "description", content: appConfig.description }]
  },
  components: { Layout, PageHeader },
  data() {
    return {
      title: "Notification",
      items: [
        {
          text: "UI Elements",
          href: "/"
        },
        {
          text: "Notification",
          active: true
        }
      ],
      toastCount: 0
    };
  },
  methods: {
    makeToast(variant = null) {
      this.$bvToast.toast("Toast body content", {
        title: `Toast ${variant || "default"}`,
        variant: variant,
        solid: true
      });
    },
    toast(toaster, append = false) {
      this.counter++;
      this.$bvToast.toast(`Toast body content`, {
        title: `Toaster`,
        toaster: toaster,
        solid: true,
        appendToast: append
      });
    }
  }
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-lg-6">
        <div class="card">
          <div class="card-body">
            <b-button @click="makeToast()">Default</b-button>
            <b-button variant="primary" @click="makeToast('primary')" class="ml-2">Primary</b-button>
            <b-button variant="secondary" @click="makeToast('secondary')" class="ml-2">Secondary</b-button>
            <b-button variant="danger" @click="makeToast('danger')" class="ml-2">Danger</b-button>
            <b-button variant="warning" @click="makeToast('warning')" class="ml-2">Warning</b-button>
            <b-button variant="success" @click="makeToast('success')" class="ml-2">Success</b-button>
            <b-button variant="info" @click="makeToast('info')" class="ml-2">Info</b-button>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-8">
        <div class="card">
          <div class="card-body">
            <b-button @click="toast('b-toaster-top-right')" class="ml-2" variant="light">Top-right</b-button>
            <b-button @click="toast('b-toaster-top-left')" class="ml-2" variant="light">Top-left</b-button>
            <b-button @click="toast('b-toaster-top-center')" class="ml-2" variant="light">Top-center</b-button>
            <b-button @click="toast('b-toaster-top-full')" class="ml-2" variant="light">Top-full</b-button>
            <b-button
              @click="toast('b-toaster-bottom-right', true)"
              class="ml-2"
              variant="light"
            >Bottom-right</b-button>
            <b-button
              @click="toast('b-toaster-bottom-left', true)"
              class="ml-2"
              variant="light"
            >Bottom-left</b-button>
            <b-button
              @click="toast('b-toaster-bottom-center', true)"
              class="ml-2"
              variant="light"
            >Bottom-center</b-button>
            <b-button
              @click="toast('b-toaster-bottom-full', true)"
              class="ml-2"
              variant="light"
            >Bottom-full</b-button>
          </div>
        </div>
      </div>
    </div>
  </Layout>
</template>