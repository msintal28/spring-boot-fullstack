import {
    Box,
    Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerContent, DrawerFooter,
    DrawerHeader,
    DrawerOverlay, FormControl, FormErrorMessage, FormLabel, Input,
    useDisclosure
} from "@chakra-ui/react";
import CreateCustomerForm from "./shared/CreateCustomerForm.jsx";
import {ErrorMessage, Field, Form, Formik} from "formik";
import * as Yup from "yup";
import {uploadFile} from "../services/client.js";
import {failureNotification, successNotification} from "../services/notification.js";

const CloseIcon = () => "x"
const UploadImageDrawerForm = ({id}) => {
    const {isOpen, onOpen, onClose} = useDisclosure()
    return (
        <>
            <Button
                colorScheme={"blue"}
                onClick={onOpen}
            >
                Upload Image
            </Button>
            <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
                <DrawerOverlay/>
                <DrawerContent>
                    <DrawerCloseButton/>
                    <DrawerHeader>Create new customer</DrawerHeader>

                    <DrawerBody>
                        <UploadImageForm
                            customerId={id}
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

const UploadImageForm = ({customerId}) => {
    const validationSchema = Yup.object().shape({
        image: Yup.mixed().required('Image is required'),
    });

    return (<Box maxW="md" mx="auto" mt={8}>
            <Formik
                initialValues={{image: null}}
                validationSchema={validationSchema}
                onSubmit={(values, {setSubmitting}) => {
                    const formData = new FormData();
                    formData.append('file', values.image);
                    setSubmitting(true)
                    uploadFile(formData, customerId)
                        .then(() => {
                            successNotification(
                                "Image uploaded",
                                `${values.image.name} was successfully saved`)
                        }).catch(err => {
                        failureNotification(
                            err.code,
                            err.response.data.message
                        )
                    }).finally(() => {
                        setSubmitting(false)
                    })
                }}
            >
                {({isValid, isSubmitting}) => (
                    <Form>
                        <Field name="image">
                            {({field, form}) => (
                                <FormControl isInvalid={form.errors.image && form.touched.image}>
                                    <FormLabel>Upload Image</FormLabel>
                                    <Input
                                        type="file"
                                        accept="image/*"
                                        onChange={(event) => form.setFieldValue('image', event.currentTarget.files[0])}
                                    />
                                    <ErrorMessage name="image" component={FormErrorMessage}/>
                                </FormControl>
                            )}
                        </Field>

                        <Button isDisabled={!isValid || isSubmitting} mt={4} colorScheme="teal" type="submit">
                            Upload
                        </Button>
                    </Form>
                )}
            </Formik>
        </Box>
    )
}

export default UploadImageDrawerForm;