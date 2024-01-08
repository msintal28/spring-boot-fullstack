import { Line } from "react-chartjs-2";
function LineChart({ chartData }) {
    return (
        <div className="chart-container">
            <h2 style={{ textAlign: "center" }}>Line Chart</h2>
            <Line
                data={{
                    labels: ['2022-01-01', '2022-01-02', '2022-01-03', '2022-01-04'],
                    datasets: [
                        {
                            label: 'Sample Line Chart',
                            data: [10, 15, 7, 22],
                            borderColor: 'rgba(75,192,192,1)',
                            borderWidth: 2,
                            pointRadius: 5,
                            pointHoverRadius: 8,
                            fill: false,
                        },
                    ],
                }}
                options={{
                    responsive:true
                }}
            />
        </div>
    );
}
export default LineChart;