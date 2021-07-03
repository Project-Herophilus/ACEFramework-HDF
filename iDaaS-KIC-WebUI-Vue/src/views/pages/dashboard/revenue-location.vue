<script>
/**
 * Revenue by Locations component
 */
export default {
  data() {
    return {
      series: [
        {
          name: "South",
          data: [12, 32, 10, 15, 20, 32],
        },
        {
          name: "North",
          data: [18, 12, 25, 25, 15, 20],
        },
        {
          name: "Central",
          data: [12, 30, 20, 25, 27, 25],
        },
      ],
      chartOptions: {
        chart: {
          type: "area",
          height: 196,
          stacked: true,
          toolbar: {
            show: false,
          },
        },
        colors: ["#5438DC", "#564ab1", "#5664d2"],
        dataLabels: {
          enabled: false,
        },
        stroke: {
          width: 1,
          curve: "smooth",
        },
        fill: {
          type: "gradient",
          gradient: {
            opacityFrom: 0.6,
            opacityTo: 0.8,
          },
        },
        legend: {
          show: false,
        },
        labels: {
          show: false,
        },
        xaxis: {
          labels: {
            show: false,
          },
        },
         yaxis: {
          labels: {
            show: false,
          },
        },
      },
    };
  },
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

      <h4 class="card-title mb-4">Revenue by Locations</h4>
      <apexchart
        type="area"
        height="182"
        :options="chartOptions"
        :series="series"
      ></apexchart>

      <div class="row justify-content-center">
        <div class="col-xl-5 col-md-6">
          <div class="mt-2">
            <div class="clearfix py-2">
              <h5 class="float-right font-size-16 m-0">$ 2542</h5>
              <p class="text-muted mb-0 text-truncate">California :</p>
            </div>
            <div class="clearfix py-2">
              <h5 class="float-right font-size-16 m-0">$ 2245</h5>
              <p class="text-muted mb-0 text-truncate">Nevada :</p>
            </div>
          </div>
        </div>
        <div class="col-xl-5 offset-xl-1 col-md-6">
          <div class="mt-2">
            <div class="clearfix py-2">
              <h5 class="float-right font-size-16 m-0">$ 2156</h5>
              <p class="text-muted mb-0 text-truncate">Montana :</p>
            </div>
            <div class="clearfix py-2">
              <h5 class="float-right font-size-16 m-0">$ 1845</h5>
              <p class="text-muted mb-0 text-truncate">Texas :</p>
            </div>
          </div>
        </div>
      </div>
      <div class="text-center mt-4">
        <a href="#" class="btn btn-primary btn-sm">Learn more</a>
      </div>
    </div>
  </div>
</template>