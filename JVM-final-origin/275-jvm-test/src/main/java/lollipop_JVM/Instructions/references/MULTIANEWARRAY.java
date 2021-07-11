package lollipop_JVM.Instructions.references;

import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import lollipop_JVM.Instructions.base.Instruction;
import lollipop_JVM.runtimeDataArea.JHeap;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.ArrayObject;
import lollipop_JVM.runtimeDataArea.struct.array.MultiArrayObject;

import java.nio.ByteBuffer;

public class MULTIANEWARRAY extends Instruction {
    //todo
    //for test
    private static int a = 1;
    private int index;
    private int dimensions;
    @Override
    public void execute(StackFrame frame) {
        a++;
        ClassRef classRef = (ClassRef)frame.getMethod().getClazz().getRuntimeConstantPool().getConstant(this.index);
        try{
            JClass jClass = classRef.getResolvedClass();

            //get length of each dimension
            int[] arrLen = new int[dimensions];
            for (int i = dimensions-1; i >=0 ; i--) {
                arrLen[i] = frame.getOperandStack().popInt();
                assert arrLen[i] >= 0;
            }


            //create multiArray
            ArrayObject arrayObject = this.createANewDimensionArray(0,arrLen,jClass);
            JHeap.getInstance().addObj(arrayObject);
            frame.getOperandStack().pushObjectRef(arrayObject);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fetchOperands(ByteBuffer byteBuffer) {
        this.index = byteBuffer.getShort() & '\uffff';
        this.dimensions = byteBuffer.get() & 255;
        assert dimensions >= 1;
    }

    private ArrayObject createANewDimensionArray(int index, int[] arrLen, JClass arrayClass){
        ArrayObject arrayObject = arrayClass.getArrayObject(arrLen[index]);
        index ++ ;
        if (index < arrLen.length){
            for (int i = 0; i < arrayObject.getLen(); i++) {
                ((MultiArrayObject)arrayObject).getArray()[i] = this.createANewDimensionArray(index, arrLen, arrayClass.getComponentClass());
            }
        }
        return arrayObject;
    }
}
