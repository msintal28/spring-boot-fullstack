import {Center, Flex, Stack, Text, Wrap, WrapItem} from "@chakra-ui/react";
import SidebarWithHeader from "./components/shared/SideBar.jsx";
import PieChart from "./components/charts/PieChart.jsx";
import Chart from "chart.js/auto";
import {CategoryScale} from "chart.js";
import LineChart from "./components/charts/LineChart.jsx";
import {BarChart} from "./components/charts/BarChart.jsx";

Chart.register(CategoryScale);

const data = {
    labels: ['Red', 'Orange', 'Blue', 'Green'],
    // datasets is an array of objects where each object represents a set of data to display corresponding to the labels above. for brevity, we'll keep it at one object
    datasets: [
        {
            label: 'Popularity of colours',
            data: [55, 23, 96, 44],
            // you can set indiviual colors for each bar
            backgroundColor: [
                'rgba(0, 0, 255, 0.6)',
                'rgba(0, 255, 255, 0.6)',
                'rgba(255, 0, 0, 0.6)',
                'rgba(0, 255, 0, 0.6)'
            ],
            borderWidth: 2,
        }
    ]
}


function Home() {
    // const [chartData, setChartData] = useState({
    //     // ...chart data
    // });

    return (
        <SidebarWithHeader>
            <Text fontSize={"6xl"}>Dashboard</Text>
            <Wrap
                justify={"center"}
                spacing={"20px"}
                >
                <WrapItem><Center w='400px' h='400px'><PieChart chartData={data}/></Center></WrapItem>
                <WrapItem><Center w='400px' h='400px'><LineChart chartData={data}/></Center></WrapItem>
                <WrapItem><Center w='400px' h='400px'><BarChart chartData={data}/></Center></WrapItem>
            </Wrap>
        </SidebarWithHeader>
    )

}

export default Home
