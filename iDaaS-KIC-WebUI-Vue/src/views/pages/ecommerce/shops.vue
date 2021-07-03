<script>
import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";

/**
 * Shops Component
 */
export default {
  components: {
    Layout,
    PageHeader
  },
  data() {
    return {
      title: "Shops",
      items: [
        {
          text: "Ecommerce"
        },
        {
          text: "Shops",
          active: true
        }
      ],
      shopsData: [
        {
          image: require("@/assets/images/companies/img-1.png"),
          title: "Nedick's",
          name: "Wayne McClain",
          products: 86,
          balance: "$12,456"
        },
        {
          image: require("@/assets/images/companies/img-2.png"),
          title: "Brendle's",
          name: "David Marshall",
          products: 72,
          balance: "$10,352"
        },
        {
          image: require("@/assets/images/companies/img-3.png"),
          title: "Tech Hifi",
          name: "Katia Stapleton",
          products: 75,
          balance: "$9,963"
        },
        {
          image: require("@/assets/images/companies/img-4.png"),
          title: "Lafayette",
          name: "Andrew Bivens",
          products: 65,
          balance: "$14,568"
        },
        {
          image: require("@/assets/images/companies/img-5.png"),
          title: "Packer",
          name: "Mae Rankin",
          products: 82,
          balance: "$16,445"
        },
        {
          image: require("@/assets/images/companies/img-6.png"),
          title: "Micro Design",
          name: " Brian Correa",
          products: 71,
          balance: "$11,523"
        },
        {
          image: require("@/assets/images/companies/img-7.png"),
          title: "Keeney's",
          name: "Dean Odom",
          products: 66,
          balance: "$13,478"
        },
        {
          image: require("@/assets/images/companies/img-8.png"),
          title: "Tech Hifi",
          name: "John McLeroy",
          products: 58,
          balance: "$14,654"
        }
      ]
    };
  }
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-xl-3 col-sm-6" v-for="(data, index) in shopsData" :key="index">
        <div class="card">
          <div class="card-body">
            <div class="text-center">
              <img :src="`${data.image}`" alt class="avatar-sm mt-2 mb-4" />
              <div class="media-body">
                <h5 class="text-truncate">
                  <a href="#" class="text-dark">{{data.title}}</a>
                </h5>
                <p class="text-muted">
                  <i class="mdi mdi-account mr-1"></i> {{data.name}}
                </p>
              </div>
            </div>
            <hr class="my-4" />
            <div class="row text-center">
              <div class="col-6">
                <p class="text-muted mb-2">Products</p>
                <h5>{{data.products}}</h5>
              </div>
              <div class="col-6">
                <p class="text-muted mb-2">Wallet Balance</p>
                <h5>{{data.balance}}</h5>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end row -->

    <div class="row">
      <div class="col-xl-12">
        <div class="text-center my-3">
          <a href="javascript:void(0);" class="text-primary">
            <i class="mdi mdi-loading mdi-spin font-size-20 align-middle mr-2"></i> Load more
          </a>
        </div>
      </div>
    </div>
    <!-- end row -->
  </Layout>
</template>