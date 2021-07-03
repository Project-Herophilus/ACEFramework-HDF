<script>
import simplebar from "simplebar-vue";

import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";

import { chatData, chatMessagesData } from "./data";

import { required } from "vuelidate/lib/validators";

/**
 * Chat Component
 */
export default {
  page: {
    title: "Chat",
    meta: [{ name: "description" }],
  },
  components: { Layout, PageHeader, simplebar },
  data() {
    return {
      chatData: chatData,
      chatMessagesData: chatMessagesData,
      title: "Chat",
      items: [
        {
          text: "Skote",
          href: "/",
        },
        {
          text: "Chat",
          active: true,
        },
      ],
      submitted: false,
      form: {
        message: "",
      },
      username: "Frank Vickery",
    };
  },
  validations: {
    form: {
      message: { required },
    },
  },
  methods: {
    /**
     * Get the name of user
     */
    chatUsername(name, image) {
      this.image = image;
      this.username = name;
      this.usermessage = "Hello";
      this.chatMessagesData = [];
      const currentDate = new Date();

      this.chatMessagesData.push({
        name: this.username,
        message: this.usermessage,
        time: currentDate.getHours() + ":" + currentDate.getMinutes(),
        image: this.image,
      });
    },

    /**
     * Char form Submit
     */
    // eslint-disable-next-line no-unused-vars
    formSubmit(e) {
      this.submitted = true;

      // stop here if form is invalid
      this.$v.$touch();

      if (this.$v.$invalid) {
        return;
      } else {
        const message = this.form.message;
        const currentDate = new Date();
        this.chatMessagesData.push({
          align: "right",
          name: "Henry Wells",
          message,
          time: currentDate.getHours() + ":" + currentDate.getMinutes(),
        });
        this.handleScroll();
      }
      this.submitted = false;
      this.form = {};
    },

    handleScroll() {
      if (this.$refs.current.$el) {
        setTimeout(() => {
          this.$refs.current.SimpleBar.getScrollElement().scrollTop =
            this.$refs.current.SimpleBar.getScrollElement().scrollHeight + 1500;
        }, 500);
      }
    },
  },
  mounted() {
    var container = document.querySelector(
      "#scrollElement .simplebar-content-wrapper"
    );
    console.log(container)
    container.scrollTo({ top: 200, behavior: "smooth" });

    var container2 = document.querySelector(
      "#containerElement .simplebar-content-wrapper"
    );
    container2.scrollTo({ top: 500, behavior: "smooth" });
  },
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="d-lg-flex mb-4">
      <div class="chat-leftsidebar">
        <div class="p-3 border-bottom">
          <div class="media">
            <div class="align-self-center mr-3">
              <img
                src="@/assets/images/users/avatar-2.jpg"
                class="avatar-xs rounded-circle"
                alt
              />
            </div>
            <div class="media-body">
              <h5 class="font-size-15 mt-0 mb-1">Ricky Clark</h5>
              <p class="text-muted mb-0">
                <i class="mdi mdi-circle text-success align-middle mr-1"></i>
                Active
              </p>
            </div>
            <div>
              <b-dropdown class="chat-noti-dropdown" right variant="white">
                <template v-slot:button-content>
                  <i class="mdi mdi-dots-horizontal font-size-20"></i>
                </template>
                <b-dropdown-item>Action</b-dropdown-item>
                <b-dropdown-item>Another action</b-dropdown-item>
                <b-dropdown-item>Something else here</b-dropdown-item>
              </b-dropdown>
            </div>
          </div>
        </div>

        <div class="card-body border-bottom py-2">
          <div class="search-box chat-search-box">
            <div class="position-relative">
              <input type="text" class="form-control" placeholder="Search..." />
              <i class="ri-search-line search-icon"></i>
            </div>
          </div>
        </div>

        <div class="chat-leftsidebar-nav">
          <b-tabs pills fill content-class="py-4" justified>
            <b-tab title="Tab 1" active>
              <template v-slot:title>
                <i class="ri-message-2-line font-size-20"></i>
                <span class="mt-2 d-none d-sm-block">Chat</span>
              </template>
              <b-card-text>
                <div>
                  <h5 class="font-size-14 px-3 mb-3">Recent</h5>
                  <simplebar style="max-height: 345px" id="scrollElement">
                    <ul class="list-unstyled chat-list">
                      <li
                        class
                        v-for="data of chatData"
                        :key="data.id"
                        @click="chatUsername(data.name, data.image)"
                        :class="{ active: username == data.name }"
                      >
                        <a href="javascript: void(0);">
                          <div class="media">
                            <div
                              class="user-img align-self-center mr-3"
                              v-if="data.image"
                              :class="{
                                online: `${data.status}` === 'online',
                                away: `${data.status}` === 'away',
                              }"
                            >
                              <img
                                :src="`${data.image}`"
                                class="rounded-circle avatar-xs"
                                alt
                              />
                              <span class="user-status"></span>
                            </div>
                            <div
                              class="user-img mr-3"
                              v-if="!data.image"
                              :class="{
                                online: `${data.status}` === 'online',
                                away: `${data.status}` === 'away',
                              }"
                            >
                              <div class="avatar-xs align-self-center">
                                <span
                                  class="avatar-title rounded-circle bg-light text-body"
                                  >{{ data.name.charAt(0) }}</span
                                >
                              </div>
                              <span class="user-status"></span>
                            </div>
                            <div class="media-body overflow-hidden">
                              <h5 class="text-truncate font-size-14 mb-1">
                                {{ data.name }}
                              </h5>
                              <p class="text-truncate mb-0">
                                {{ data.message }}
                              </p>
                            </div>
                            <div class="font-size-11">{{ data.time }}</div>
                          </div>
                        </a>
                      </li>
                    </ul>
                  </simplebar>
                </div>
              </b-card-text>
            </b-tab>
            <b-tab>
              <template v-slot:title>
                <i class="ri-group-line font-size-20"></i>
                <span class="mt-2 d-none d-sm-block">Group</span>
              </template>
              <b-card-text>
                <h5 class="font-size-14 px-3 mb-3">Group</h5>
                <ul class="list-unstyled chat-list">
                  <li>
                    <a href="#">
                      <div class="media align-items-center">
                        <div class="avatar-xs mr-3">
                          <span
                            class="avatar-title rounded-circle bg-light text-body"
                            >G</span
                          >
                        </div>

                        <div class="media-body">
                          <h5 class="font-size-14 mb-0">General</h5>
                        </div>
                      </div>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <div class="media align-items-center">
                        <div class="avatar-xs mr-3">
                          <span
                            class="avatar-title rounded-circle bg-light text-body"
                            >R</span
                          >
                        </div>

                        <div class="media-body">
                          <h5 class="font-size-14 mb-0">Reporting</h5>
                        </div>
                      </div>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <div class="media align-items-center">
                        <div class="avatar-xs mr-3">
                          <span
                            class="avatar-title rounded-circle bg-light text-body"
                            >M</span
                          >
                        </div>

                        <div class="media-body">
                          <h5 class="font-size-14 mb-0">Meeting</h5>
                        </div>
                      </div>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <div class="media align-items-center">
                        <div class="avatar-xs mr-3">
                          <span
                            class="avatar-title rounded-circle bg-light text-body"
                            >A</span
                          >
                        </div>

                        <div class="media-body">
                          <h5 class="font-size-14 mb-0">Project A</h5>
                        </div>
                      </div>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <div class="media align-items-center">
                        <div class="avatar-xs mr-3">
                          <span
                            class="avatar-title rounded-circle bg-light text-body"
                            >B</span
                          >
                        </div>

                        <div class="media-body">
                          <h5 class="font-size-14 mb-0">Project B</h5>
                        </div>
                      </div>
                    </a>
                  </li>
                </ul>
              </b-card-text>
            </b-tab>
            <b-tab>
              <template v-slot:title>
                <i class="ri-contacts-book-2-line font-size-20"></i>
                <span class="mt-2 d-none d-sm-block">Contacts</span>
              </template>
              <b-card-text>
                <h5 class="font-size-14 px-3 mb-3">Contact</h5>
                <simplebar style="height: 345px">
                  <div>
                    <div class="p-3">A</div>

                    <ul class="list-unstyled chat-list">
                      <li>
                        <a href="#">
                          <h5 class="font-size-14 mb-0">Adam Miller</h5>
                        </a>
                      </li>

                      <li>
                        <a href="#">
                          <h5 class="font-size-14 mb-0">Alfonso Fisher</h5>
                        </a>
                      </li>
                    </ul>
                  </div>

                  <div class="mt-4">
                    <div class="p-3">B</div>

                    <ul class="list-unstyled chat-list">
                      <li>
                        <a href="#">
                          <h5 class="font-size-14 mb-0">Bonnie Harney</h5>
                        </a>
                      </li>
                    </ul>
                  </div>

                  <div class="mt-4">
                    <div class="p-3">C</div>

                    <ul class="list-unstyled chat-list">
                      <li>
                        <a href="#">
                          <h5 class="font-size-14 mb-0">Charles Brown</h5>
                        </a>
                        <a href="#">
                          <h5 class="font-size-14 mb-0">Carmella Jones</h5>
                        </a>
                        <a href="#">
                          <h5 class="font-size-14 mb-0">Carrie Williams</h5>
                        </a>
                      </li>
                    </ul>
                  </div>

                  <div class="mt-4">
                    <div class="p-3">D</div>

                    <ul class="list-unstyled chat-list">
                      <li>
                        <a href="#">
                          <h5 class="font-size-14 mb-0">Dolores Minter</h5>
                        </a>
                      </li>
                    </ul>
                  </div>
                </simplebar>
              </b-card-text>
            </b-tab>
          </b-tabs>
        </div>
      </div>
      <div class="w-100 user-chat mt-4 mt-sm-0">
        <div class="p-3 px-lg-4 user-chat-border">
          <div class="row">
            <div class="col-md-4 col-6">
              <h5 class="font-size-15 mb-1 text-truncate">{{ username }}</h5>
              <p class="text-muted text-truncate mb-0">
                <i class="mdi mdi-circle text-success align-middle mr-1"></i>
                Active now
              </p>
            </div>
            <div class="col-md-8 col-3">
              <ul class="list-inline user-chat-nav text-right mb-0">
                <li class="list-inline-item d-inline-block d-sm-none">
                  <div class="dropdown">
                    <button
                      class="btn nav-btn dropdown-toggle"
                      type="button"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      <i class="mdi mdi-magnify"></i>
                    </button>
                    <div
                      class="dropdown-menu dropdown-menu-right dropdown-menu-md"
                    >
                      <form class="p-2">
                        <div class="search-box">
                          <div class="position-relative">
                            <input
                              type="text"
                              class="form-control rounded"
                              placeholder="Search..."
                            />
                            <i class="mdi mdi-magnify search-icon"></i>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </li>
                <li class="d-none d-sm-inline-block">
                  <div class="search-box mr-2">
                    <div class="position-relative">
                      <input
                        type="text"
                        class="form-control"
                        placeholder="Search..."
                      />
                      <i class="mdi mdi-magnify search-icon"></i>
                    </div>
                  </div>
                </li>
                <li class="list-inline-item d-none d-sm-inline-block">
                  <b-dropdown toggle-class="nav-btn" right variant="white">
                    <template v-slot:button-content>
                      <i class="mdi mdi-cog"></i>
                    </template>
                    <b-dropdown-item>View Profile</b-dropdown-item>
                    <b-dropdown-item>Clear chat</b-dropdown-item>
                    <b-dropdown-item>Muted</b-dropdown-item>
                    <b-dropdown-item>Delete</b-dropdown-item>
                  </b-dropdown>
                </li>

                <li class="list-inline-item">
                  <b-dropdown toggle-class="nav-btn" right variant="white">
                    <template v-slot:button-content>
                      <i class="mdi mdi-dots-horizontal"></i>
                    </template>
                    <b-dropdown-item>Action</b-dropdown-item>
                    <b-dropdown-item>Another action</b-dropdown-item>
                    <b-dropdown-item>Something else</b-dropdown-item>
                  </b-dropdown>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div class="px-lg-2 chat-users">
          <div class="chat-conversation p-3">
            <simplebar
              style="max-height: 450px"
              id="containerElement"
              ref="current"
            >
              <ul class="list-unstyled mb-0 pr-3">
                <li>
                  <div class="chat-day-title">
                    <span class="title">Today</span>
                  </div>
                </li>
                <li
                  v-for="data of chatMessagesData"
                  :key="data.message"
                  :class="{ right: `${data.align}` === 'right' }"
                >
                  <div class="conversation-list">
                    <div class="chat-avatar" v-if="data.image">
                      <img :src="`${data.image}`" alt />
                    </div>

                    <div class="ctext-wrap">
                      <div class="conversation-name">{{ data.name }}</div>
                      <div class="ctext-wrap-content">
                        <p class="mb-0">{{ data.message }}</p>
                      </div>

                      <p class="chat-time mb-0">
                        <i class="bx bx-time-five align-middle mr-1"></i>
                        {{ data.time }}
                      </p>
                    </div>
                  </div>
                </li>
              </ul>
            </simplebar>
          </div>
          <div class="px-lg-3">
            <div class="p-3 chat-input-section">
              <form @submit.prevent="formSubmit" class="row">
                <div class="col">
                  <div class="position-relative">
                    <input
                      type="text"
                      v-model="form.message"
                      class="form-control chat-input"
                      placeholder="Enter Message..."
                      :class="{
                        'is-invalid': submitted && $v.form.message.$error,
                      }"
                    />
                    <div
                      v-if="submitted && $v.form.message.$error"
                      class="invalid-feedback"
                    >
                      <span v-if="!$v.form.message.required"
                        >This value is required.</span
                      >
                    </div>
                  </div>
                </div>
                <div class="col-auto">
                  <button
                    type="submit"
                    class="btn btn-primary chat-send w-md waves-effect waves-light"
                  >
                    <span class="d-none d-sm-inline-block mr-2">Send</span>
                    <i class="mdi mdi-send"></i>
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- end row -->
  </Layout>
</template>