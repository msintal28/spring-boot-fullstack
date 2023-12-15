import {
    Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerContent, DrawerFooter,
    DrawerHeader,
    DrawerOverlay, Input,
    useDisclosure
} from "@chakra-ui/react";
import CreateCustomerForm from "./CreateCustomerForm.jsx";

const AddIcon = () => "+"
const CloseIcon = () => "x"
const DrawerForm = ({ fetchCustomers }) => {
    const {isOpen, onOpen, onClose} = useDisclosure()
    return (
        <>
            <Button
                colorScheme={"teal"}
                leftIcon={<AddIcon/>}
                onClick={onOpen}
            >
                Create customer
            </Button>
            <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
                <DrawerOverlay/>
                <DrawerContent>
                    <DrawerCloseButton/>
                    <DrawerHeader>Create new customer</DrawerHeader>

                    <DrawerBody>
                        <CreateCustomerForm
                            fetchCustomers={fetchCustomers}
                        />
                    </DrawerBody>

                    <DrawerFooter>
                        <Button
                            colorScheme={"teal"}
                            leftIcon={<CloseIcon/>}
                            onClick={onClose}
                        >
                            Close
                        </Button>
                    </DrawerFooter>
                </DrawerContent>
            </Drawer>
        </>
    )
}

export default DrawerForm;