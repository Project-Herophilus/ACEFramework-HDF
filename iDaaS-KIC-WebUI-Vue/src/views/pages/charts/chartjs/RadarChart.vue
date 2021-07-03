<script>
import { Radar } from "vue-chartjs";

export default {
  extends: Radar,
  mounted() {
    this.renderChart(
      {
        labels: [
          "Eating",
          "Drinking",
          "Sleeping",
          "Designing",
          "Coding",
          "Cycling",
          "Running"
        ],
        datasets: [
          {
            label: "Desktops",
            backgroundColor: "rgba(252, 185, 44, 0.2)",
            borderColor: "#fcb92c",
            pointBackgroundColor: "#fcb92c",
            pointBorderColor: "#fff",
            pointHoverBackgroundColor: "#fff",
            pointHoverBorderColor: "#fcb92c",
            data: [65, 59, 90, 81, 56, 55, 40]
          },
          {
            label: "Tablets",
            backgroundColor: "rgba(84, 56, 220, 0.2)",
            borderColor: "#5664d2",
            pointBackgroundColor: "#5664d2",
            pointBorderColor: "#fff",
            pointHoverBackgroundColor: "#fff",
            pointHoverBorderColor: "#5664d2",
            data: [28, 48, 40, 19, 96, 27, 100]
          }
        ]
      },
      {
        maintainAspectRatio: true,
        legend: {
          position: "top"
        }
      }
    );
  }
};
</script>