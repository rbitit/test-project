import java.io.*;
import java.beans.*;
public class MyBeanInfo {
    public String str_BeanInfo;
    public String my_Bean;
    
    public MyBeanInfo(String my_Bean){
        this.str_BeanInfo="";
        this.my_Bean=my_Bean;
    }    
    
    public String getMyBeanInfo() throws Exception {
        
        String str_BeanInfo="";
        Class monBeanClasse = Class.forName(my_Bean);
        if(monBeanClasse==null){
        throw new ClassNotFoundException();
        }
        MethodDescriptor[] methodDescriptor;
        BeanInfo bi = Introspector.getBeanInfo(monBeanClasse);
        BeanDescriptor unBeanDescriptor = bi.getBeanDescriptor();
        str_BeanInfo=str_BeanInfo+("Nom du bean : " + unBeanDescriptor.getName()+"\n");
        str_BeanInfo=str_BeanInfo+("Classe du bean : " + unBeanDescriptor.getBeanClass()+"\n");
        str_BeanInfo=str_BeanInfo+"\n";
        str_BeanInfo=str_BeanInfo+"Description des propriétés:\n";        
        PropertyDescriptor[] propertyDescriptor = bi.getPropertyDescriptors();
        for (int i=0; i<propertyDescriptor.length; i++) {
            str_BeanInfo=str_BeanInfo+(" Nom propriete : " +propertyDescriptor[i].getName()+"\n");
            str_BeanInfo=str_BeanInfo+(" Type propriete : "+ propertyDescriptor[i].getPropertyType()+"\n");
            str_BeanInfo=str_BeanInfo+(" Getter propriete : "+ propertyDescriptor[i].getReadMethod()+"\n");
            str_BeanInfo=str_BeanInfo+(" Setter propriete : "+ propertyDescriptor[i].getWriteMethod()+"\n\n");
        }
        str_BeanInfo=str_BeanInfo+("\n");
        methodDescriptor = bi.getMethodDescriptors();
        str_BeanInfo=str_BeanInfo+"Description des Méthodes:\n";        
        for (int i=0; i < methodDescriptor.length; i++) {
            str_BeanInfo=str_BeanInfo+(" Methode : "+methodDescriptor[i].getName()+"\n");
        }
        str_BeanInfo=str_BeanInfo+("\n");
        EventSetDescriptor[] unEventSetDescriptor = bi.getEventSetDescriptors();
        str_BeanInfo=str_BeanInfo+"Description des événements:\n";        
        for (int i = 0; i < unEventSetDescriptor.length; i++) {
            str_BeanInfo=str_BeanInfo+(" Nom evt : "+ unEventSetDescriptor[i].getName()+"\n");
            str_BeanInfo=str_BeanInfo+(" Methode add evt : " +unEventSetDescriptor[i].getAddListenerMethod()+"\n");
            str_BeanInfo=str_BeanInfo+(" Methode remove evt : " +unEventSetDescriptor[i].getRemoveListenerMethod()+"\n\n");
            methodDescriptor = unEventSetDescriptor[i].getListenerMethodDescriptors();
            for (int j = 0; j < methodDescriptor.length; j++) {
                str_BeanInfo=str_BeanInfo+(" Event Type: " + methodDescriptor[j].getName()+"\n");
            }
        }
        return (str_BeanInfo);//=str_BeanInfo+("");
    }
}