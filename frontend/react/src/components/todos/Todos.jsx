import {
    Box, Button,
    Center,
    Checkbox,
    FormControl,
    FormLabel,
    Input,
    Stack,
    Text,
    VStack,
    Wrap,
    WrapItem
} from "@chakra-ui/react";
import SidebarWithHeader from "../shared/SideBar.jsx";
import {useState} from "react";

function Todos() {
    const [checkedItems, setCheckedItems] = useState([false, false, false, false])

    const allChecked = checkedItems.every(Boolean)
    const isIndeterminate = checkedItems.some(Boolean) && !allChecked
    const [todos, setTodos] = useState([
        { text: 'Example TODO 1', dueDate: '2024-01-10' },
        { text: 'Example TODO 2', dueDate: '2024-01-10' },
        { text: 'Example TODO 3', dueDate: '2024-01-10' },
    ]);
    const [newTodo, setNewTodo] = useState('');
    const [dueDate, setDueDate] = useState('');

    const addTodo = () => {
        if (newTodo.trim() === '' || dueDate.trim() === '') {
            return;
        }

        setTodos([...todos, { text: newTodo, dueDate }]);
        setNewTodo('');
        setDueDate('');
    };
    return (
        <SidebarWithHeader>
            <Text fontSize={"6xl"}>Todos will be here soon</Text>
            <Checkbox
                isChecked={allChecked}
                isIndeterminate={isIndeterminate}
                onChange={(e) => setCheckedItems([e.target.checked, e.target.checked, e.target.checked, e.target.checked])}
            >
                Parent Checkbox
            </Checkbox>
            <Stack pl={6} mt={1} spacing={1}>
                <Checkbox
                    isChecked={checkedItems[0]}
                    onChange={(e) => setCheckedItems([e.target.checked, checkedItems[1], checkedItems[2], checkedItems[3]])
                    }
                >
                    Child Checkbox 1
                </Checkbox>
                <Checkbox
                    isChecked={checkedItems[1]}
                    onChange={(e) => setCheckedItems([checkedItems[0], e.target.checked, checkedItems[2], checkedItems[3]])}
                >
                    Child Checkbox 2
                </Checkbox>
                <Checkbox
                    isChecked={checkedItems[2]}
                    onChange={(e) => setCheckedItems([checkedItems[0], checkedItems[1],  e.target.checked, checkedItems[3]])}
                >
                    Child Checkbox 3
                </Checkbox>
                <Checkbox
                    isChecked={checkedItems[3]}
                    onChange={(e) => setCheckedItems([checkedItems[0], checkedItems[1], checkedItems[2],  e.target.checked])}
                >
                    Child Checkbox 4
                </Checkbox>
            </Stack>


            <Box p={4}>
                <VStack spacing={4} align="stretch">
                    <FormControl>
                        <FormLabel>TODO</FormLabel>
                        <Input type="text" value={newTodo} onChange={(e) => setNewTodo(e.target.value)} />
                    </FormControl>

                    <FormControl>
                        <FormLabel>Due Date</FormLabel>
                        <Input type="date" value={dueDate} onChange={(e) => setDueDate(e.target.value)} />
                    </FormControl>

                    <Button colorScheme="teal" onClick={addTodo}>
                        Add TODO
                    </Button>

                    {todos.map((todo, index) => (
                        <Box key={index} borderWidth="1px" borderRadius="md" p={4}>
                            <Text fontSize="lg">{todo.text}</Text>
                            <Text>Due Date: {todo.dueDate}</Text>
                        </Box>
                    ))}
                </VStack>
            </Box>

        </SidebarWithHeader>
    )

}

export default Todos