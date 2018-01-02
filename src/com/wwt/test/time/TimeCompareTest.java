package com.wwt.test.time;

import com.wwt.test.CommonTest;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author wwt
 * @date 2017/6/19 20:22
 */
public class TimeCompareTest extends CommonTest {

    @Test
    public void timeSortTest(){

        List<String>  entityList = new ArrayList<String>();
        entityList.add(" 09:16");
        entityList.add("08:16 ");
        entityList.add("16:16 ");
        entityList.add(" 14:16");

        final SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");

        Date now = new Date();

        Collections.sort(entityList, new Comparator<String>() {
            public int compare(String o1, String o2) {
                try{
                    Date bt=sdf.parse(o1.trim());
                    Date et=sdf.parse(o2.trim());
                    int i = 0;
                    if (bt.before(et))
                        i = -1;
                    else
                        i = 1;
                    return i;
                }catch (Exception e){
                    return -1;
                }
            }
        });
        try {
            String targetTimeStr = sdf.format(now);
            Date parse = sdf.parse(targetTimeStr);
            String target = null;
            for (int i = 0; i < entityList.size() ; i++){
                String bdPositionClassesEntity = entityList.get(i);
                Date parse1 = sdf.parse(bdPositionClassesEntity);
                if (parse.before(parse1))
                    target = bdPositionClassesEntity;
            }

            if (target == null){
                Calendar cal = Calendar.getInstance();
                cal.setTime(now);
                cal.add(Calendar.DATE, 1);
                sysOut(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
            }
            sysOut(target);
        }catch (Exception e){

        }

    }
}
