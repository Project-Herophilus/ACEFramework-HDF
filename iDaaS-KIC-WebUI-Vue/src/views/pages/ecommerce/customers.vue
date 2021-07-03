<script>
import { required, email, minLength } from "vuelidate/lib/validators";

import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";

/**
 * Customers Component
 */
export default {
  components: {
    Layout,
    PageHeader
  },
  data() {
    return {
      title: "Customers",
      items: [
        {
          text: "Ecommerce"
        },
        {
          text: "Customers",
          active: true
        }
      ],
      customersData: [
        {
          name: "Carolyn Harvey",
          email: "CarolynHarvey@rhyta.com",
          phone: "580-464-4694",
          balance: "$ 3245",
          date: "06 Apr, 2020"
        },
        {
          name: "Angelyn Hardin",
          email: "AngelynHardin@dayrep.com",
          phone: "913-248-2690",
          balance: "$ 2435",
          date: "05 Apr, 2020"
        },
        {
          name: "Carrie Thompson",
          email: "CarrieThompson@rhyta.com",
          phone: "734-819-9286",
          balance: "$ 2653",
          date: "04 Apr, 2020"
        },
        {
          name: "Kathleen Haller",
          email: "KathleenHaller@dayrep.com",
          phone: "313-742-3333",
          balance: "$ 2135",
          date: "03 Apr, 2020"
        },
        {
          name: "Martha Beasley",
          email: "MarthaBeasley@teleworm.us",
          phone: "301-330-5745",
          balance: "$ 2698",
          date: "02 Apr, 2020"
        },
        {
          name: "Kathryn Hudson",
          email: "KathrynHudson@armyspy.com",
          phone: "414-453-5725",
          balance: "$ 2758",
          date: "02 Apr, 2020"
        },
        {
          name: "Robert Bott",
          email: "RobertBott@armyspy.com",
          phone: "712-237-9899",
          balance: "$ 2836",
          date: "01 Apr, 2020"
        },
        {
          name: "Mary McDonald",
          email: "MaryMcDonald@armyspy.com",
          phone: "317-510-25554",
          balance: "$ 3245",
          date: "31 Mar, 2020"
        },
        {
          name: "Keith Rainey",
          email: "KeithRainey@jourrapide.com",
          phone: "214-712-1810",
          balance: "$ 3125",
          date: "30 Mar, 2020"
        },
        {
          name: "Anthony Russo",
          email: "AnthonyRusso@jourrapide.com",
          phone: "412-371-8864",
          balance: "$ 2456",
          date: "30 Mar, 2020"
        },
        {
          name: "Donna Betz",
          email: "DonnaBetz@jourrapide.com",
          phone: "626-583-5779",
          balance: "$ 3423",
          date: "29 Mar, 2020"
        },
        {
          name: "Angie Andres",
          email: "AngieAndres@armyspy.com",
          phone: "213-494-4527",
          balance: "$ 3245",
          date: "28 Apr, 2020"
        }
      ],
      customers: {
        name: "",
        email: "",
        balance: "",
        phone: "",
        date: ""
      },
      submitted: false,
      showmodal: false
    };
  },
  validations: {
    customers: {
      name: { required },
      balance: { required },
      email: { required, email },
      phone: { required, minLength: minLength(10) },
      date: { required }
    }
  },
  methods: {
    /**
     * Modal form submit
     */
    // eslint-disable-next-line no-unused-vars
    handleSubmit(e) {
      this.submitted = true;

      // stop here if form is invalid
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      } else {
        const name = this.customers.name;
        const balance = this.customers.balance;
        const email = this.customers.email;
        const phone = this.customers.phone;
        const date = this.customers.date;
        this.customersData.push({
          name,
          email,
          balance,
          phone,
          date
        });
        this.showmodal = false;
      }
      this.submitted = false;
      this.customers = {};
    },
    /**
     * hode mondal on close
     */
    // eslint-disable-next-line no-unused-vars
    hideModal(e) {
      this.submitted = false;
      this.showmodal = false;
      this.contacts = {};
    },

    /**
     * Filter the data of search
     */
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    }
  }
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div>
              <a href="javascript:void(0);" class="btn btn-success mb-2" @click="showmodal = true">
                <i class="mdi mdi-plus mr-2"></i> Add Customer
              </a>
            </div>
            <div class="table-responsive mt-3">
              <table
                class="table table-centered datatable dt-responsive nowrap"
                style="border-collapse: collapse; border-spacing: 0; width: 100%;"
              >
                <thead class="thead-light">
                  <tr>
                    <th style="width: 20px;">
                      <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="customercheck" />
                        <label class="custom-control-label" for="customercheck">&nbsp;</label>
                      </div>
                    </th>
                    <th>Customer</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Wallet Balance</th>
                    <th>Joining Date</th>
                    <th style="width: 120px;">Action</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in customersData" :key="index">
                    <td>
                      <div class="custom-control custom-checkbox">
                        <input
                          type="checkbox"
                          class="custom-control-input"
                          :id="`customercheck${index}`"
                        />
                        <label class="custom-control-label" :for="`customercheck${index}`">&nbsp;</label>
                      </div>
                    </td>
                    <td>{{item.name}}</td>
                    <td>{{item.email}}</td>
                    <td>{{item.phone}}</td>
                    <td>{{item.balance}}</td>
                    <td>{{item.date}}</td>
                    <td>
                      <a
                        href="javascript:void(0);"
                        class="mr-3 text-primary"
                        v-b-tooltip.hover
                        title="Edit"
                      >
                        <i class="mdi mdi-pencil font-size-18"></i>
                      </a>
                      <a
                        href="javascript:void(0);"
                        class="text-danger"
                        v-b-tooltip.hover
                        title="Delete"
                      >
                        <i class="mdi mdi-trash-can font-size-18"></i>
                      </a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal -->
    <b-modal
      id="modal-1"
      v-model="showmodal"
      title="Add Customer"
      title-class="text-dark font-18"
      hide-footer
    >
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="name">Name</label>
          <input
            id="name"
            v-model="customers.name"
            type="text"
            class="form-control"
            placeholder="Enter name"
            :class="{ 'is-invalid': submitted && $v.customers.name.$error }"
          />
          <div
            v-if="submitted && !$v.customers.name.required"
            class="invalid-feedback"
          >Name is required</div>
        </div>
        <div class="form-group">
          <label for="exampleInputEmail1">Email</label>
          <input
            id="email"
            v-model="customers.email"
            type="email"
            name="email"
            class="form-control"
            placeholder="Enter email"
            :class="{ 'is-invalid': submitted && $v.customers.email.$error }"
          />
          <div v-if="submitted && $v.customers.email.$error" class="invalid-feedback">
            <span v-if="!$v.customers.email.required">Email is required</span>
            <span v-if="!$v.customers.email.email">Email is invalid</span>
          </div>
        </div>
        <div class="form-group">
          <label for="position">Phone</label>
          <input
            id="position"
            v-model="customers.phone"
            type="text"
            class="form-control"
            placeholder="Enter phone number"
            :class="{ 'is-invalid': submitted && $v.customers.phone.$error }"
          />
          <div
            v-if="submitted && !$v.customers.phone.required"
            class="invalid-feedback"
          >Phone is required</div>
        </div>
        <div class="form-group">
          <label for="company">Balance</label>
          <input
            id="company"
            v-model="customers.balance"
            type="text"
            class="form-control"
            placeholder="Enter balance"
            :class="{ 'is-invalid': submitted && $v.customers.balance.$error }"
          />
          <div
            v-if="submitted && !$v.customers.balance.required"
            class="invalid-feedback"
          >Balance is required</div>
        </div>
        <div class="form-group">
          <label for="position">Joining Date</label>
          <input
            id="position"
            v-model="customers.date"
            type="text"
            class="form-control"
            placeholder="Enter Joining Date"
            :class="{ 'is-invalid': submitted && $v.customers.date.$error }"
          />
          <div
            v-if="submitted && !$v.customers.date.required"
            class="invalid-feedback"
          >Date is required</div>
        </div>
        <div class="text-right">
          <button type="submit" class="btn btn-success">Save</button>
          <b-button class="ml-1" variant="danger" @click="hideModal">Cancel</b-button>
        </div>
      </form>
    </b-modal>
    <!-- end modal -->
  </Layout>
</template>