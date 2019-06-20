<script>
import { Bar } from "vue-chartjs";
import { mixins } from "vue-chartjs";
const { reactiveProp } = mixins;

export default {
  name: "Chart",
  extends: Bar,
  props: {
    data: {
      type: Object,
      default: function() {
        return {
          labels: "None",
          datasets: [
            {
              label: "Number of people",
              backgroundColor: "#f87979",
              color: "black",
              data: []
            }
          ]
        };
      }
    }
  },
  mounted() {
    this.renderChart(this.data, this.options);
  }
};
</script>
