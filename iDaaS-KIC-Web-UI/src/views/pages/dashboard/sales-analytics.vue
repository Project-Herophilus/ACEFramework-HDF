<script>
/**
 * Sales Analytics component
 */
export default {
  data() {
    return {
      series: [42, 26, 15],
      chartOptions: {
        chart: {
          height: 230,
          type: "donut"
        },
        labels: ["Product A", "Product B", "Product C"],
        plotOptions: {
          pie: {
            donut: {
              size: "75%"
            }
          }
        },
        dataLabels: {
          enabled: false
        },
        legend: {
          show: false
        },
        colors: ["#5664d2", "#1cbb8c", "#eeb902"]
      }
    };
  }
};
</script>

<template>
  <div class="card">
    <div class="card-body">
      <div class="float-right">
        <select class="custom-select custom-select-sm">
          <option selected>Apr</option>
          <option value="1">Mar</option>
          <option value="2">Feb</option>
          <option value="3">Jan</option>
        </select>
      </div>
      <h4 class="card-title mb-4">Sales Analytics</h4>

      <div id="donut-chart" class="apex-charts"></div>
      <apexchart
        class="apex-charts"
        height="230"
        dir="ltr"
        :series="series"
        :options="chartOptions"
      ></apexchart>
      <div class="row">
        <div class="col-4">
          <div class="text-center mt-4">
            <p class="mb-2 text-truncate">
              <i class="mdi mdi-circle text-primary font-size-10 mr-1"></i> Product A
            </p>
            <h5>42 %</h5>
          </div>
        </div>
        <div class="col-4">
          <div class="text-center mt-4">
            <p class="mb-2 text-truncate">
              <i class="mdi mdi-circle text-success font-size-10 mr-1"></i> Product B
            </p>
            <h5>26 %</h5>
          </div>
        </div>
        <div class="col-4">
          <div class="text-center mt-4">
            <p class="mb-2 text-truncate">
              <i class="mdi mdi-circle text-warning font-size-10 mr-1"></i> Product C
            </p>
            <h5>42 %</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>