<script>
/**
 * Recent activity component
 */
export default {
  data() {
    return {
      activityData: [
        {
          icon: "ri-edit-2-fill",
          date: "28 Apr, 2020 ",
          time: "12:07 am",
          title: "Responded to need “Volunteer Activities”"
        },
        {
          icon: "ri-user-2-fill",
          date: "21 Apr, 2020 ",
          time: "08:01 pm",
          title: "Added an interest “Volunteer Activities”"
        },
        {
          icon: "ri-bar-chart-fill",
          date: "17 Apr, 2020 ",
          time: "05:10 pm",
          title: "Responded to need “In-Kind Opportunity”"
        },
        {
          icon: "ri-calendar-2-fill",
          date: "07 Apr, 2020",
          time: "12:47 pm",
          title: "Created need “Volunteer Activities”"
        },
        {
          icon: "ri-edit-2-fill",
          date: "05 Apr, 2020 ",
          time: "03:09 pm",
          title: "Attending the event “Some New Event”"
        },
        {
          icon: "ri-user-2-fill",
          date: "02 Apr, 2020 ",
          time: "12:07 am",
          title: "Responded to need “In-Kind Opportunity”"
        }
      ]
    };
  }
};
</script>

<template>
  <div class="card">
    <div class="card-body">
      <b-dropdown right toggle-class="arrow-none card-drop" class="float-right" variant="white">
        <template v-slot:button-content>
          <i class="mdi mdi-dots-vertical"></i>
        </template>
        <!-- item-->
        <b-dropdown-item>Sales Report</b-dropdown-item>
        <!-- item-->
        <b-dropdown-item>Export Report</b-dropdown-item>
        <!-- item-->
        <b-dropdown-item>Profit</b-dropdown-item>
        <!-- item-->
        <b-dropdown-item>Action</b-dropdown-item>
      </b-dropdown>

      <h4 class="card-title mb-4">Recent Activity Feed</h4>

      <div data-simplebar style="max-height: 330px;">
        <ul class="list-unstyled activity-wid">
          <li class="activity-list" v-for="(data, index) in activityData" :key="index">
            <div class="activity-icon avatar-xs">
              <span class="avatar-title bg-soft-primary text-primary rounded-circle">
                <i :class="`${data.icon}`"></i>
              </span>
            </div>
            <div>
              <div>
                <h5 class="font-size-13">
                  {{data.date}}
                  <small class="text-muted">{{data.time}}</small>
                </h5>
              </div>

              <div>
                <p class="text-muted mb-0">{{data.title}}</p>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>