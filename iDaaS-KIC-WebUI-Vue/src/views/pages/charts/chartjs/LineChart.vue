<script>
import { Line } from "vue-chartjs";

export default {
  extends: Line,
  mounted() {
    this.renderChart(
      {
        labels: [
          "January",
          "February",
          "March",
          "April",
          "May",
          "June",
          "July",
          "August",
          "September",
          "October"
        ],
        datasets: [
          {
            label: "Sales Analytics",
            fill: true,
            lineTension: 0.5,
            backgroundColor: "rgba(85, 110, 230, 0.2)",
            borderColor: "#5664d2",
            borderCapStyle: "butt",
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: "miter",
            pointBorderColor: "#5664d2",
            pointBackgroundColor: "#fff",
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: "#5664d2",
            pointHoverBorderColor: "#fff",
            pointHoverBorderWidth: 2,
            pointRadius: 1,
            pointHitRadius: 10,
            data: [65, 59, 80, 81, 56, 55, 40, 55, 30, 80]
          },
          {
            label: "Monthly Earnings",
            fill: true,
            lineTension: 0.5,
            backgroundColor: "rgba(252, 185, 44, 0.2)",
            borderColor: "#fcb92c",
            borderCapStyle: "butt",
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: "miter",
            pointBorderColor: "#fcb92c",
            pointBackgroundColor: "#fff",
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: "#fcb92c",
            pointHoverBorderColor: "#eef0f2",
            pointHoverBorderWidth: 2,
            pointRadius: 1,
            pointHitRadius: 10,
            data: [80, 23, 56, 65, 23, 35, 85, 25, 92, 36]
          }
        ]
      },
      {
        defaultFontColor: "#8791af",
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          xAxes: [
            {
              gridLines: {
                color: "rgba(166, 176, 207, 0.1)"
              }
            }
          ],
          yAxes: [
            {
              ticks: {
                max: 100,
                min: 20,
                stepSize: 10
              },
              gridLines: {
                color: "rgba(166, 176, 207, 0.1)"
              }
            }
          ]
        }
      }
    );
  }
};
</script>