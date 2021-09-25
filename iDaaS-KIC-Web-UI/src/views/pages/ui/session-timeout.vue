<script>
import Swal from "sweetalert2";

import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";
import appConfig from "@/app.config";

export default {
  page: {
    title: "Session Timeout",
    meta: [{ name: "description", content: appConfig.description }]
  },
  components: { Layout, PageHeader },
  data() {
    return {
      title: "Session Timeout",
      items: [
        {
          text: "UI Elements",
          href: "/"
        },
        {
          text: "Session Timeout",
          active: true
        }
      ],
      timeralert: null,
      timerswitch: false,
      seconds: 0
    };
  },
  mounted() {
    this.main_function();
  },
  methods: {
    main_function() {
      setTimeout(
        function() {
          setTimeout(
            function() {
              this.function1();
            }.bind(this),
            6000
          );
          this.function2();
        }.bind(this),
        6000
      );
    },
    tick() {
      this.interval = setInterval(() => {
        this.seconds = this.seconds + 1;
      }, 1000);
    },
    function1() {
      if (window.location.pathname === "/ui/session-timeout") {
        window.location = "/login";
      }
    },
    function2() {
      this.tick();
      Swal.fire({
        showCancelButton: true,
        confirmButtonText: "Stay Connected",
        cancelBtnText: "Logout",
        confirmBtnBsStyle: "success",
        cancelBtnBsStyle: "danger",
        title: "Your Session is About to Expire!",
        onClose: () => this.hideAlert(),
        html: "Redirecting in 10s seconds"
      });
    }
  }
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-12">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Bootstrap-session-timeout</h5>
            <p class="sub-header">
              Session timeout and keep-alive control
              with a nice Bootstrap warning dialog.
            </p>

            <div>
              <p>
                After a set amount of idle time, a Bootstrap warning dialog is shown
                to the user with the option to either log out, or stay connected. If
                "Logout" button is selected, the page is redirected to a logout URL.
                If "Stay Connected" is selected the dialog closes and the session is
                kept alive. If no option is selected after another set amount of
                idle time, the page is automatically redirected to a set timeout
                URL.
              </p>

              <p>Idle time is defined as no mouse, keyboard or touch event activity registered by the browser.</p>

              <p class="mb-0">
                As long as the user is active, the (optional) keep-alive URL keeps
                getting pinged and the session stays alive. If you have no need to
                keep the server-side session alive via the keep-alive URL, you can
                also use this plugin as a simple lock mechanism that redirects to
                your lock-session or log-out URL after a set amount of idle time.
              </p>
            </div>
          </div>
        </div>
      </div>
      <!-- end col -->
    </div>
    <!-- end row -->
  </Layout>
</template>