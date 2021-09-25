<script>
import Layout from "../../../layouts/main";
import PageHeader from "@/components/page-header";

import { icons } from "./data";

/**
 * Remix Icons component
 */
export default {
  page: {
    title: "Remix Icons",
    meta: [{ name: "description" }]
  },
  components: { Layout, PageHeader },
  data() {
    return {
      icons: icons,
      title: "Remix Icons",
      items: [
        {
          text: "Icons",
          href: "/"
        },
        {
          text: "Remix Icons",
          active: true
        }
      ],
      iconData: "",
      iconList: JSON.parse(icons)
    };
  },
  mounted() {
    // eslint-disable-next-line no-unused-vars
    Object.entries(this.iconList).map((key, value) => {
      if (key[0] === "Editor") {
        this.iconData =
          this.iconData +
          '<div class="card"><div class="card-body"><h4 class="card-title">' +
          key[0] +
          ' </h4><p class="card-title-desc mb-2">Use <code>&lt;i class="ri-bold"&gt;&lt;/i&gt;</code> <span class="badge badge-success">v 2.4.1</span>.</p><div class="row icon-demo-content">';
        for (let k in key[1]) {
          this.iconData +=
            '<div class="col-xl-3 col-lg-4 col-sm-6">\
                    <i class="ri-' +
            k +
            '"></i> ri-' +
            k +
            "</div>";
        }
      } else {
        this.iconData =
          this.iconData +
          '<div class="card"><div class="card-body"><h4 class="card-title">' +
          key[0] +
          ' </h4><p class="card-title-desc mb-2">Use <code>&lt;i class="ri-home-line"&gt;&lt;/i&gt;</code> or <code>&lt;i class="ri-home-fill"&gt;&lt;/i&gt;</code> <span class="badge badge-success">v 2.4.1</span>.</p><div class="row icon-demo-content">';
        for (let k in key[1]) {
          this.iconData +=
            '<div class="col-xl-3 col-lg-4 col-sm-6">\
                    <i class="ri-' +
            k +
            '-line"></i> ri-' +
            k +
            '-line\
                </div><div class="col-xl-3 col-lg-4 col-sm-6">\
                    <i class="ri-' +
            k +
            '-fill"></i> ri-' +
            k +
            "-fill\
                </div>";
        }
      }
      this.iconData += "</div></div></div>";
    });
    document.getElementById("icons").innerHTML = this.iconData;
  }
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-12" id="icons"></div>
      <!-- end col-->
    </div>
    <!-- end row-->
  </Layout>
</template>