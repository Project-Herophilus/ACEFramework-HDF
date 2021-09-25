<script>
import { required } from "vuelidate/lib/validators";
import simplebar from "simplebar-vue";

/**
 * Chat component
 */
export default {
  components: { simplebar },
  data() {
    return {
      submitted: false,
      chat: {
        message: "",
      },
      ChatData: [
        {
          id: 1,
          name: "Frank Vickery",
          message: "Hey! I am available",
          image: require("@/assets/images/users/avatar-2.jpg"),
          time: "12:09",
        },
        {
          id: 2,
          align: "right",
          name: "Ricky Clark",
          message: "Hi, How are you? What about our next meeting?",
          time: "10:02",
        },
        {
          text: "Today",
        },
        {
          id: 3,
          name: "Frank Vickery",
          message: "Hello!",
          image: require("@/assets/images/users/avatar-2.jpg"),
          time: "10:06",
        },
        {
          id: 4,
          name: "Frank Vickery",
          message: "& Next meeting tomorrow 10.00AM",
          image: require("@/assets/images/users/avatar-2.jpg"),
          time: "10:06",
        },
        {
          id: 5,
          align: "right",
          name: "Ricky Clark",
          message: "Wow that's great",
          time: "10:07",
        },
      ],
    };
  },
  validations: {
    chat: {
      message: { required },
    },
  },
  mounted() {
    var container2 = document.querySelector(
      "#containerElement .simplebar-content-wrapper"
    );
    container2.scrollTo({ top: 500, behavior: "smooth" });
  },
  methods: {
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
        const id = this.ChatData.length;
        const message = this.chat.message;
        const currentDate = new Date();
        this.ChatData.push({
          id: id + 1,
          align: "right",
          name: "Ricky Clark",
          message,
          time: currentDate.getHours() + ":" + currentDate.getMinutes(),
        });
        this.handleScroll();
      }
      this.submitted = false;
      this.chat = {};
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
};
</script>

<template>
  <div class="card">
    <div class="card-body border-bottom">
      <div class="user-chat-border">
        <div class="row">
          <div class="col-md-5 col-9">
            <h5 class="font-size-15 mb-1">Frank Vickery</h5>
            <p class="text-muted mb-0">
              <i class="mdi mdi-circle text-success align-middle mr-1"></i>
              Active now
            </p>
          </div>
          <div class="col-md-7 col-3">
            <ul class="list-inline user-chat-nav text-right mb-0">
              <li class="list-inline-item">
                <b-nav-item-dropdown
                  toggle-class="nav-btn"
                  right
                  variant="black"
                  menu-class="dropdown-menu-md p-0"
                >
                  <template v-slot:button-content>
                    <i class="mdi mdi-magnify"></i>
                  </template>
                  <form class="p-2">
                    <div class="search-box">
                      <div class="position-relative">
                        <input
                          type="text"
                          class="form-control rounded bg-light border-0"
                          placeholder="Search..."
                        />
                        <i class="mdi mdi-magnify search-icon"></i>
                      </div>
                    </div>
                  </form>
                </b-nav-item-dropdown>
              </li>
              <li class="list-inline-item d-none d-sm-inline-block">
                <b-nav-item-dropdown
                  toggle-class="nav-btn"
                  right
                  variant="black"
                >
                  <template v-slot:button-content>
                    <i class="mdi mdi-cog"></i>
                  </template>
                  <a class="dropdown-item" href="#">View Profile</a>
                  <a class="dropdown-item" href="#">Clear chat</a>
                  <a class="dropdown-item" href="#">Muted</a>
                  <a class="dropdown-item" href="#">Delete</a>
                </b-nav-item-dropdown>
              </li>

              <li class="list-inline-item">
                <b-nav-item-dropdown
                  toggle-class="nav-btn"
                  right
                  variant="black"
                >
                  <template v-slot:button-content>
                    <i class="mdi mdi-dots-horizontal"></i>
                  </template>
                  <a class="dropdown-item" href="#">Action</a>
                  <a class="dropdown-item" href="#">Another action</a>
                  <a class="dropdown-item" href="#">Something else</a>
                </b-nav-item-dropdown>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="card-body">
      <div class="chat-widget">
        <div class="chat-conversation">
          <simplebar
            style="max-height: 292px"
            ref="current"
            id="containerElement"
          >
            <ul
              class="list-unstyled mb-0 pr-3"
              v-for="(data, index) in ChatData"
              :key="index"
              :class="{ right: `${data.align}` === 'right' }"
            >
              <li v-if="!data.text">
                <div class="conversation-list">
                  <div v-if="data.image" class="chat-avatar">
                    <img :src="`${data.image}`" alt />
                  </div>
                  <div class="ctext-wrap">
                    <div class="conversation-name">{{ data.name }}</div>
                    <div class="ctext-wrap-content">
                      <p class="mb-0">{{ data.message }}</p>
                    </div>
                    <p class="chat-time mb-0">
                      <i class="mdi mdi-clock-outline align-middle mr-1"></i>
                      {{ data.time }}
                    </p>
                  </div>
                </div>
              </li>

              <li v-if="data.text">
                <div class="chat-day-title">
                  <span class="title">Today</span>
                </div>
              </li>
            </ul>
          </simplebar>
        </div>
      </div>
    </div>
    <div class="p-3 chat-input-section border-top">
      <form @submit.prevent="formSubmit" class="row">
        <div class="col">
          <div>
            <input
              type="text"
              class="form-control rounded chat-input pl-3"
              placeholder="Enter Message..."
              v-model="chat.message"
              :class="{ 'is-invalid': submitted && $v.chat.message.$error }"
            />
            <div
              v-if="submitted && $v.chat.message.$error"
              class="invalid-feedback"
            >
              <span v-if="!$v.chat.message.required"
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
</template>