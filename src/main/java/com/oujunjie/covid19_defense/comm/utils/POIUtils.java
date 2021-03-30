package com.oujunjie.covid19_defense.comm.utils;

import com.oujunjie.covid19_defense.comm.my_exception.exceptions.POIException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Component
public class POIUtils {

    public static List<Object[]> handerImportExcel(InputStream inputStream) {
        try {
            List<Object[]> list = new ArrayList<>();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rows; i++) {
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                    if (cell.getCellType().name().equals("NUMERIC")) {
                        objects[index] = (int) cell.getNumericCellValue();
                    }else if (cell.getCellType().name().equals("STRING")) {
                        objects[index] = cell.getStringCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new POIException("excel format fail,please check excel");
        }
    }


}
