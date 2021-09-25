<script>
import { PolarArea } from "vue-chartjs";

export default {
  extends: PolarArea,
  mounted() {
    this.renderChart(
      {
        labels: ["Series 1", "Series 2", "Series 3", "Series 4"],
        datasets: [
          {
            data: [11, 16, 7, 18],
            backgroundColor: ["#fcb92c", "#1cbb8c", "#ff3d60", "#5664d2"],
            label: "My dataset", // for legend
            hoverBorderColor: "#fff"
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