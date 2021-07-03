<script>
import VueEasyLightbox from "vue-easy-lightbox";

import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";
import appConfig from "@/app.config";

/**
 * Lightbox component
 */
export default {
  page: {
    title: "Lightbox",
    meta: [{ name: "description", content: appConfig.description }]
  },
  components: { VueEasyLightbox, Layout, PageHeader },
  data() {
    return {
      title: "Lightbox",
      items: [
        {
          text: "UI Elements",
          href: "/"
        },
        {
          text: "Lightbox",
          active: true
        }
      ],
      imgs: "", // Img Url , string or Array of string
      visible: false,
      index: 0, // default: 0
      lightboximgs: [
        require("@/assets/images/small/img-1.jpg"),
        require("@/assets/images/small/img-2.jpg"),
        require("@/assets/images/small/img-3.jpg"),
        require("@/assets/images/small/img-4.jpg"),
        require("@/assets/images/small/img-5.jpg"),
        require("@/assets/images/small/img-6.jpg")
      ]
    };
  },
  methods: {
    showSingle() {
      this.imgs = require("@/assets/images/small/img-2.jpg");
      this.show();
    },
    show() {
      this.visible = true;
    },
    handleHide() {
      this.visible = false;
    },
    showImg(index) {
      this.index = index;
      this.visible = true;
    }
  }
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-xl-6">
        <div class="card">
          <div class="card-body">
            <h4 class="card-title">Single image lightbox</h4>
            <p class="card-title-desc">Three simple popups with different scaling settings.</p>

            <div class="row">
              <div class="col-6">
                <div>
                  <h5 class="mt-0 font-size-14">Fits (Horz/Vert)</h5>
                  <a class="image-popup-vertical-fit" @click="showSingle">
                    <img class="img-fluid" alt src="@/assets/images/small/img-2.jpg" width="145" />
                  </a>
                  <vue-easy-lightbox :visible="visible" :imgs="imgs" @hide="handleHide"></vue-easy-lightbox>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-xl-6">
        <div class="card">
          <div class="card-body">
            <h4 class="card-title">Lightbox gallery</h4>
            <p
              class="card-title-desc"
            >In this example lazy-loading of images is enabled for the next image based on move direction.</p>

            <div class="popup-gallery">
              <a
                class="float-left"
                v-for="(item, index) in lightboximgs"
                :key="index"
                @click="() => showImg(index)"
              >
                <div class="img-fluid">
                  <img :src="`${item}`" alt width="120" />
                </div>
              </a>
            </div>
            <vue-easy-lightbox
              :visible="visible"
              :index="index"
              :imgs="lightboximgs"
              @hide="visible = false"
            ></vue-easy-lightbox>
          </div>
        </div>
      </div>
      <!-- end col -->
    </div>
    <!-- end row -->

    <div class="row">
      <div class="col-xl-6">
        <div class="card">
          <div class="card-body">
            <h4 class="card-title">Popup with video or map</h4>
            <p
              class="card-title-desc"
            >In this example lazy-loading of images is enabled for the next image based on move direction.</p>

            <div class="row">
              <div class="col-12">
                <div class="button-items">
                  <b-button v-b-modal.modal-1 class="mo-mb-2" variant="light">Open YouTube Video</b-button>
                  <b-modal
                    class="dark-video-modal"
                    id="modal-1"
                    hide-footer
                    size="lg"
                    centered
                    header-close-variant="white"
                    modal-class="video-modal"
                  >
                    <youtube video-id="RnDC9MXSqCY" ref="youtube" width="750" height="450"></youtube>
                  </b-modal>

                  <a
                    class="popup-vimeo btn btn-light mo-mb-2"
                    href="https://vimeo.com/45830194"
                  >Open Vimeo Video</a>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="card">
          <div class="card-body">
            <h4 class="card-title mb-4">Popup with form</h4>
            <div>
              <a
                class="popup-form btn btn-primary"
                href="javascript: void(0);"
                v-b-modal.popup-form
              >Popup form</a>
            </div>
            <b-modal id="popup-form" hide-footer centered size="lg" title="Form">
              <form>
                <div class="row">
                  <div class="col-lg-4">
                    <div class="form-group">
                      <label for="name">Name</label>
                      <input type="text" class="form-control" id="name" placeholder="Enter Name" />
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" placeholder="Enter Email" />
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="form-group">
                      <label for="password">Password</label>
                      <input
                        type="password"
                        class="form-control"
                        id="password"
                        placeholder="Enter Password"
                      />
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-lg-12">
                    <div class="form-group">
                      <label for="subject">Subject</label>
                      <textarea class="form-control" id="subject" rows="3"></textarea>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-lg-12">
                    <div class="text-right">
                      <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                  </div>
                </div>
              </form>
            </b-modal>
          </div>
        </div>
      </div>
      <!-- end col -->
    </div>
    <!-- end row -->
  </Layout>
</template>

<style scoped>
::v-deep .dark-video-modal .modal-content {
  background-color: #000000 !important;
  color: white !important;
  background: none !important;
  border: none !important;
}
::v-deep .modal-header {
  border: none;
}
::v-deep .modal-backdrop {
  opacity: 0.5;
}
::v-deep .video-modal .modal-dialog {
  max-width: 800px;
}
</style>