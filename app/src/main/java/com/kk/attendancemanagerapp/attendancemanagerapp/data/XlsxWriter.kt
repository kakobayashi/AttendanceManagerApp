package com.kk.attendancemanagerapp.attendancemanagerapp.data

// TODO poiがうまく利用できないためエクセル出力は一旦保留
class XlsxWriter {

    fun createXlsx() {
//        var book: Workbook? = null
//        var fileOut: FileOutputStream? = null
//
//        try {
//            book = SXSSFWorkbook()
//
//            val font: Font = book.createFont()
//            font.fontName = "MS ゴシック"
//            font.fontHeightInPoints = 9
//
//            val format: DataFormat = book.createDataFormat()
//
//            // ヘッダの字列用のスタイル
//            val styleHeader: CellStyle = book.createCellStyle()
//            styleHeader.borderBottom = BorderStyle.THIN
//            setBorder(styleHeader, BorderStyle.THIN)
//            styleHeader.fillForegroundColor = HSSFColor.HSSFColorPredefined.LIGHT_BLUE.index
//            styleHeader.fillPattern = FillPatternType.SOLID_FOREGROUND
//            styleHeader.verticalAlignment = VerticalAlignment.TOP
//            styleHeader.setFont(font)
//
//            var row: Row
//            var rowNumber: Int
//            var cell: Cell
//            var colNumber: Int
//
//            var sheet: Sheet
//
//            // シート作成
//            sheet = book.createSheet()
//            sheet.trackAllColumnsForAutoSizing()
//
//            // シート名称の設定
//            book.setSheetName(0, "シート1")
//
//            // ヘッダ行の作成
//            rowNumber = 0
//            colNumber = 0
//
//            row = sheet.createRow(rowNumber)
//            cell = row.createCell(colNumber++)
//            cell.cellStyle = styleHeader
//            cell.cellType = CellType.STRING
//            cell.setCellValue("No.")
//
//            cell = row.createCell(colNumber++)
//            cell.cellStyle = styleHeader
//            cell.cellType = CellType.STRING
//            cell.setCellValue("文字列")
//
//            // ウィンドウ枠の固定
//            sheet.createFreezePane(1, 1)
//
//            // ヘッダ行にオートフィルタの設定
//            sheet.setAutoFilter(CellRangeAddress(0, 0, 0, colNumber))
//
//            // ファイル出力
//            fileOut = FileOutputStream(OUTPUT_FILE_NAME)
//            book.write(fileOut)
//
//            Log.d("kkkk", "write" )
//
//        } catch (e: Exception) {
//            // do nothing.
//        } finally {
//            if (fileOut != null) {
//                try {
//                    fileOut.close()
//                } catch (e: IOException) {
//                    // do nothing.
//                }
//            }
//
//            if (book != null) {
//                try {
//                    (book as SXSSFWorkbook).dispose()
//                } catch (e: Exception) {
//                    // do nothing.
//                }
//            }
//        }
    }

    companion object {
//        fun setBorder(style: CellStyle, border: BorderStyle) {
//            style.borderBottom = border
//            style.borderTop = border
//            style.borderLeft = border
//            style.borderRight = border
//        }

        const val OUTPUT_FILE_NAME: String = "hoge.xlsx"
    }
}