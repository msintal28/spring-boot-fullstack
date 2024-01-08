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
import UpdateCustomerFrom from "./UpdateCustomerForm.jsx";
const CloseIcon = () => "x"
const UpdateDrawerForm = ({ fetchCustomers, id, initialValues}) => {
    const {isOpen, onOpen, onClose} = useDisclosure()
    return (
        <>
            <Button
                colorScheme={"teal"}
                onClick={onOpen}
            >
                Update Customer
            </Button>
            <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
                <DrawerOverlay/>
                <DrawerContent>
                    <DrawerCloseButton/>
                    <DrawerHeader>Update customer</DrawerHeader>

                    <DrawerBody>
                        <UpdateCustomerFrom
                            fetchCustomers={fetchCustomers}
                            id={id}
                            initialValues={initialValues}
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

export default UpdateDrawerForm;