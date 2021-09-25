<script>
import Layout from "../../../layouts/main";
import PageHeader from "@/components/page-header";

import { timelineData } from "./data";

/**
 * Timeline component
 */
export default {
  page: {
    title: "Timeline",
    meta: [{ name: "description" }]
  },
  components: { Layout, PageHeader },
  data() {
    return {
      timelineData: timelineData,
      title: "Timeline",
      items: [
        {
          text: "Utility",
          href: "/"
        },
        {
          text: "Timeline",
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
    <div class="row justify-content-center">
      <div class="col-xl-10">
        <div class="timeline" dir="ltr">
          <div class="timeline-item timeline-left">
            <div class="timeline-block">
              <div class="time-show-btn mt-0">
                <a href="#" class="btn btn-danger btn-rounded w-lg">2020</a>
              </div>
            </div>
          </div>

          <div
            class="timeline-item"
            v-for="(data, index) in timelineData"
            :key="index"
            :class="{'timeline-left': `${data.align}` === 'left'}"
          >
            <div class="timeline-block">
              <div class="timeline-box card">
                <div class="card-body">
                  <span class="timeline-icon"></span>
                  <div class="timeline-date">
                    <i class="mdi mdi-circle-medium circle-dot"></i>
                    {{data.date}}
                  </div>
                  <h5 class="mt-3 foont-size-15">{{data.title}}</h5>
                  <div class="text-muted">
                    <p class="mb-0">{{data.text}}</p>
                  </div>
                  <div class="timeline-album" v-if="data.images">
                    <a href="#" class="mr-1">
                      <img :src="`${data.images[0]}`" alt />
                    </a>
                    <a href="#" class="mr-1">
                      <img :src="`${data.images[1]}`" alt />
                    </a>
                    <a href="#" class="mr-1">
                      <img :src="`${data.images[2]}`" alt />
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </Layout>
</template>