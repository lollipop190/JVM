package com.njuse.seecjvm.memory.jclass;

import com.njuse.seecjvm.classloader.classfileparser.MethodInfo;
import com.njuse.seecjvm.classloader.classfileparser.attribute.CodeAttribute;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Method extends ClassMember {
    private int maxStack;
    private int maxLocal;
    private int argc;
    private byte[] code;

    public Method(MethodInfo info, JClass clazz) {
        this.clazz = clazz;
        accessFlags = info.getAccessFlags();
        name = info.getName();
        descriptor = info.getDescriptor();

        CodeAttribute codeAttribute = info.getCodeAttribute();
        if (codeAttribute != null) {
            maxLocal = codeAttribute.getMaxLocal();
            maxStack = codeAttribute.getMaxStack();
            code = codeAttribute.getCode();
        }
        argc = calculateArgcFromDescriptor(descriptor);
    }
    //todo calculateArgcFromDescriptor
    private int calculateArgcFromDescriptor(String descriptor) {

        char[] all_descriptor_char = descriptor.toCharArray();
        char[] descriptor_char = new char[all_descriptor_char.length];
        for (int i = 0 ;i <all_descriptor_char.length;i++){
            if (all_descriptor_char[i+1] == ')')
                break;
            descriptor_char[i] = all_descriptor_char[i+1];
        }
        int space = 0;
        boolean is_array = false;
        boolean is_className = false;
        for (char descriptor_in_char:descriptor_char) {
            switch (descriptor_in_char){
                case '[':
                    is_array = true;
                    break;
                case 'L':
                    is_className = true;
                    break;
                case ';':
                    if (!is_array){
                    space++;
                    is_className = false;
                    }else if (is_className){
                        space++;
                        is_array = false;
                        is_className = false;
                    }
                    break;
                case 'J':
                case 'D':
                    if (!is_array && !is_className)
                        space += 2;
                    else if (is_array)
                        space ++;
                        is_array = false;
                    break;
                case 0:
                    break;
                default:
                    if (!is_array && !is_className)
                      space++;
                    else if (!is_className && is_array){
                        space++;
                        is_array = false;
                    }

                    //                case '':
//                    break;
//                case '':
//                    break;
//                case '':
//                    break;
//                case '':
//                    break;
//                case '':
//                    break;
            }


        }
        /**
         * Add some codes here.
         * Here are some examples in README!!!
         *
         * You should refer to JVM specification for more details
         *
         * Beware of long and double type
         */

        return space;
    }
}
