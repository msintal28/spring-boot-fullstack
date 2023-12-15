import {Formik, Form, useField} from 'formik';
import * as Yup from 'yup';
import {Alert, AlertIcon, Box, Button, FormLabel, Input, Select, Stack} from '@chakra-ui/react'
import {saveCustomer, updateCustomer} from "../services/client.js";
import {failureNotification, successNotification} from "../services/notification.js";

const MyTextInput = ({label, ...props}) => {
    // useField() returns [formik.getFieldProps(), formik.getFieldMeta()]
    // which we can spread on <input>. We can use field meta to show an error
    // message if the field is invalid and it has been touched (i.e. visited)
    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Input className="text-input" {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert status='error' className="error" mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

const MySelect = ({label, ...props}) => {
    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Select {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert status='error' className="error" mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

// And now we can use these
const UpdateCustomerFrom = ({fetchCustomers, id, initialValues}) => {
    return (
        <>
            <Formik
                initialValues={initialValues}
                validationSchema={Yup.object({
                    name: Yup.string(),
                    email: Yup.string()
                        .email('Invalid email address'),
                    age: Yup.number()
                        .min(16, 'Must be at least 16 years of age')
                        .typeError("Age must be a number")
                        .nullable(),
                    gender: Yup.string()
                        .oneOf(
                            ['MALE', 'FEMALE'],
                            'Invalid gender'
                        )
                })}
                onSubmit={(customer, {setSubmitting}) => {
                    setSubmitting(true)
                    updateCustomer(customer, id)
                        .then(res => {
                            successNotification(
                                "Customer Updated",
                                `${customer.name} was successfully updated`)
                            fetchCustomers()
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
                {({isValid, isSubmitting, dirty}) => (
                    <Form>
                        <Stack spacing={"24px"}>

                            <MyTextInput
                                label="Name"
                                name="name"
                                type="text"
                            />

                            <MyTextInput
                                label="Email Address"
                                name="email"
                                type="email"
                            />

                            <MyTextInput
                                label="Age"
                                name="age"
                                type="number"
                            />

                            <MySelect label="Gender" name="gender">
                                <option value="">Select gender</option>
                                <option value="MALE">Male</option>
                                <option value="FEMALE">Female</option>
                            </MySelect>

                            <Button isDisabled={!(isValid && dirty) || isSubmitting} type="submit">Submit</Button>
                        </Stack>
                    </Form>
                )}
            </Formik>
        </>
    );
};

export default UpdateCustomerFrom