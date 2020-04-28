package ge.gov.tsu.studentmanagement.apiutils.service;

import ge.gov.tsu.studentmanagement.apiutils.annotations.OriginalEntity;
import ge.gov.tsu.studentmanagement.apiutils.annotations.Required;
import ge.gov.tsu.studentmanagement.rest.response.ResponseError;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


public class GeneralTools {

    public static <T, V> T scrapTableFromView(V viewInstance, Class<T> targetClass) {
        try {
            T targetInstance = targetClass.newInstance();
            Field[] formClassFields = viewInstance.getClass().getDeclaredFields();

            for (Field f : formClassFields) {
                if (f.isAnnotationPresent(OriginalEntity.class)) {
                    if (f.getAnnotation(OriginalEntity.class).value().equals(targetClass)) {
                        copyValue(targetInstance, viewInstance, f);
                    }
                }
            }
            return targetInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T, V> List<T> scrapTableFromView(V[] viewInstance, Class<T> targetClass) {
        return scrapTableFromView(Arrays.asList(viewInstance), targetClass);
    }

    public static <T, V> List<T> scrapTableFromView(List<V> viewInstance, Class<T> targetClass) {
        List<T> result = new ArrayList<T>();
        for (V vi : viewInstance) {
            result.add(scrapTableFromView(vi, targetClass));
        }
        return result;
    }

    private static List<Field> getFieldsUpTo(Class<?> startClass, Class<?> exclusiveParent) {
        List<Field> currentClassFields = new ArrayList<Field>();
        currentClassFields.addAll(Arrays.asList(startClass.getDeclaredFields()));
        Class<?> parentClass = startClass.getSuperclass();
        if (parentClass != null && (exclusiveParent == null || !(parentClass.equals(exclusiveParent)))) {
            List<Field> parentClassFields = getFieldsUpTo(parentClass, exclusiveParent);
            currentClassFields.addAll(parentClassFields);
        }
        return currentClassFields;
    }

    private List<Method> getMethodsUpTo(Class<?> startClass, Class<?> exclusiveParent) {
        List<Method> currentClassMethods = new ArrayList<Method>();
        currentClassMethods.addAll(Arrays.asList(startClass.getDeclaredMethods()));
        Class<?> parentClass = startClass.getSuperclass();
        if (parentClass != null && (exclusiveParent == null || !(parentClass.equals(exclusiveParent)))) {
            List<Method> parentClassFields = getMethodsUpTo(parentClass, exclusiveParent);
            currentClassMethods.addAll(parentClassFields);
        }
        return currentClassMethods;
    }

    public static <T> T getCloneOf(T source, String... excludeProps) throws ResponseObject {
        try {
            T destination = (T) source.getClass().newInstance();
            List<String> excludeProperties = Arrays.asList(excludeProps);
            for (Field f : getFieldsUpTo(source.getClass(), Object.class)) {
                if (Modifier.isStatic(f.getModifiers())) continue;
                if (excludeProperties.indexOf(f.getName()) > -1) continue;
                cloneValue(source, destination, f.getName());
            }
            return destination;
        } catch (Exception e) {
            throw ResponseObject.createFailedResponse(); //FIXME
        }
    }

    private static <T> void cloneValue(T sourceObject, T destinationObject, String propertyName) throws ResponseObject {
        try {
            Object sourceValue = sourceObject.getClass().getMethod(getGetterName(propertyName)).invoke(sourceObject);
            Class returnType = sourceObject.getClass().getMethod(getGetterName(propertyName)).getReturnType();
            if (sourceValue == null) return;
            destinationObject.getClass().getMethod(getSetterName(propertyName), returnType).invoke(destinationObject, sourceValue);
        } catch (Exception e) {
            throw ResponseObject.createFailedResponse();
        }

    }

    /**
     * .
     * transfers property values fromm source to dest object but keeps not null values in dest object
     *
     * @param source      current version of object
     * @param destination object with only changed properties
     * @param <T>         any type acceptable
     * @return returns dest object
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T mergeObjects(T source, T destination) throws ResponseObject {
        try {
            for (Field f : getFieldsUpTo(source.getClass(), Object.class)) {
                mergeValue(source, destination, f.getName());
            }
        } catch (Exception e) {
            throw ResponseObject.createFailedResponse();
        }
        return destination;
    }

    private static <T> void mergeValue(T sourceObject, T destinationObject, String propertyName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Object destinationValue = destinationObject.getClass().getMethod(getGetterName(propertyName)).invoke(destinationObject);
        Object sourceValue = sourceObject.getClass().getMethod(getGetterName(propertyName)).invoke(sourceObject);
        Class returnType = sourceObject.getClass().getMethod(getGetterName(propertyName)).getReturnType();
        if (destinationValue != null || sourceValue == null) return;
        destinationObject.getClass().getMethod(getSetterName(propertyName), returnType).invoke(destinationObject, sourceValue);
    }

    private static void copyValue(Object targetObject, Object sourceObject, Field sourceField) {
        try {
            Field targetField;
            Object value;
            String fieldName = sourceField.getAnnotation(OriginalEntity.class).fieldName();
            targetField = targetObject.getClass().getDeclaredField(fieldName);

            boolean sourceAcStatus = sourceField.isAccessible();
            boolean targetAcStatus = targetField.isAccessible();

            sourceField.setAccessible(true);
            value = sourceField.get(sourceObject);
            targetField.setAccessible(true);
            targetField.set(targetObject, value);
            targetField.setAccessible(targetAcStatus);
            sourceField.setAccessible(sourceAcStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void checkRequiredFields(Object objectToCheck, List<String> requiredFieldList) throws ResponseObject {
        List<ResponseError> reList = new ArrayList<>();

        for (Field f : objectToCheck.getClass().getDeclaredFields()) {
            if (requiredFieldList.indexOf(f.getName()) > -1) {
                Object val;
                try {
                    val = objectToCheck.getClass().getMethod(getGetterName(f.getName())).invoke(objectToCheck);
                } catch (Exception e) {
                    throw ResponseObject.createFailedResponse();
                }
                if (val == null) {
                    throw ResponseObject.createFailedResponse();
                }
            }
        }
        if (reList.size() > 0) {
            throw ResponseObject.createFailedResponse(reList);
        }
    }

    public static void checkRequiredFields(Object o, String... methods) throws ResponseObject {
        if (o == null) {
            throw ResponseObject.createFailedResponse();
        }
        if (methods.length == 0) {
            checkRequiredFields(o, (String) null);
        } else {
            for (String method : methods) {
                checkRequiredFields(o, method);
            }
        }
    }

    private static void checkRequiredFields(Object o, String method) throws ResponseObject {
        List<ResponseError> errors = new ArrayList<ResponseError>();

        for (Field field : getFieldsUpTo(o.getClass(), Object.class)) {
            if (field.isAnnotationPresent(Required.class)) {
                Required a = field.getDeclaredAnnotation(Required.class);
                if (a.on().length > 0) {
                    for (String v : a.on()) {
                        check(o, v, a, method, errors, field);
                    }
                } else {
                    check(o, null, a, method, errors, field);
                }
            }
        }
        if (errors.size() > 0) throw ResponseObject.createFailedResponse(errors);
    }

    private static void check(Object targetEntity, String method, Required a, String targetMethod, List<ResponseError> errors, Field field) {
        Object temp;
        try {
            if ((method == null || method.equalsIgnoreCase(targetMethod))) {
                temp = targetEntity.getClass().getMethod(getGetterName(field.getName())).invoke(targetEntity);
                if (temp == null || isEmptyString(temp, a) || isEmptyCollection(temp, a)) {
                    ResponseError re = new ResponseError(getErrorKeywordFor(field.getName()));
                    errors.add(re);
                }
                if (a.deepCheck()) {
                    checkRequiredFields(temp, targetMethod);
                }
            }
        } catch (Exception e) {
            ResponseError re = new ResponseError("MSDA_UTILS_CANT_CHECK_REQUIRED_FIELD");
            re.setMessage(re.getMessage() + " \n" + "cant check required field \"" + field.getName() + "\". public getter is not presented in class");
            throw ResponseObject.createFailedResponse(re);
        }
    }

    private static boolean isEmptyString(Object o, Required annotation) {
        if (!(o instanceof String) || annotation.allowEmptyValue()) {
            return false;
        }
        for (Character chars : ((String) o).toCharArray()) {
            if (!Character.isWhitespace(chars)) return false;
        }
        return true;
    }

    private static boolean isEmptyCollection(Object o, Required annotation) {
        if (!(o instanceof Collection) || annotation.allowEmptyValue()) {
            return false;
        }
        if (((Collection) o).size() == 0) {
            return true;
        }
        return false;
    }

    public static String getGetterName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
    }

    public static String getSetterName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
    }

    /**
     * New
     */
    private static <T> void mergeValueFromTo(T sourceObject, T destinationObject, String propertyName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        Method srcGetterMethod = null;
        try {
            srcGetterMethod = sourceObject.getClass().getMethod(getGetterName(propertyName));
        } catch (Exception ex) {
            System.out.println("source getter method not found");
            return;
        }
        Object sourceValue = srcGetterMethod.invoke(sourceObject);

        Method destGetterMethod = null;
        try {
            destGetterMethod = destinationObject.getClass().getMethod(getGetterName(propertyName));
        } catch (Exception ex) {
            System.out.println("destination getter method not found");
            return;
        }
        Object destinationValue = destGetterMethod.invoke(destinationObject);

        Class returnType = srcGetterMethod.getReturnType();

        if (sourceValue == destinationValue) return;

        Method destSetterMethod = null;
        try {
            destSetterMethod = destinationObject.getClass().getMethod(getSetterName(propertyName), returnType);
        } catch (Exception ex) {
            System.out.println("destination setter method not found");
            return;
        }
        destSetterMethod.invoke(destinationObject, sourceValue);
    }

    /**
     * @param source
     * @param destination
     * @param <T>
     * @return
     * @throws ResponseObject
     */
    public static <T> T mergeObjectsFromTo(T source, T destination) throws ResponseObject {
        try {
            for (Field f : getFieldsUpTo(source.getClass(), Object.class)) {
                mergeValueFromTo(source, destination, f.getName());
            }
        } catch (Exception e) {
            throw ResponseObject.createFailedResponse();
        }
        return destination;
    }

    public static void checkRequiredProperties(Object objectToCheck, List<String> requiredFieldList) throws ResponseObject {
        List<String> errorKeywords = new ArrayList<String>();

        for (String property : requiredFieldList) {
            boolean isRequiredPropertyPresent = true;
            for (Field f : objectToCheck.getClass().getDeclaredFields()) {
                Object value = null;
                try {
                    value = objectToCheck.getClass().getMethod(getGetterName(property)).invoke(objectToCheck);
                } catch (Exception e) {
                    isRequiredPropertyPresent = false;
                }
                if (value == null) {
                    isRequiredPropertyPresent = false;
                }
            }
            if (!isRequiredPropertyPresent) {
                errorKeywords.add(camelCaseToUnderscoreUpperCase(property) + "_REQUIRED");
            }
        }

        if (errorKeywords.size() > 0) {
            throw ResponseObject.createFailedResponse();
        }
    }

    private static String getErrorKeywordFor(String keyword) {
        return camelCaseToUnderscoreUpperCase(keyword) + "_REQUIRED";
    }

    public static String camelCaseToUnderscoreUpperCase(String keyword) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return keyword.replaceAll(regex, replacement).toUpperCase();
    }

    public static boolean checkDuplicate(List<?> objects, List<String> fieldList) {
        if (objects == null | objects.size() == 0 | fieldList == null | fieldList.size() == 0) {
            return false;
        }
        Set<String> hashSet = new HashSet<String>();

        for (Object object : objects) {
            String unionHashCodeString = "";
            for (String field : fieldList) {
                try {
                    int hashOfField = object.getClass().getMethod(getGetterName(field)).invoke(object).hashCode();
                    unionHashCodeString += "_" + hashOfField;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (hashSet.contains(unionHashCodeString)) {
                return true;
            }
            hashSet.add(unionHashCodeString);
        }
        return false;
    }

    public static <T extends Object, K extends Object> void edit(T source, K destination, String method) {

    }

}