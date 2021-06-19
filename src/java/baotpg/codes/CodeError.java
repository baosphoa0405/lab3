/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.codes;

/**
 *
 * @author Admin
 */
public class CodeError {

    private String codeIDEmpty, codeValueEmpty, codeIDduplicate, codeIDFormat, codeValueFormat;

    @Override
    public String toString() {
        return "CodeError{" + "codeIDEmpty=" + codeIDEmpty + ", codeValueEmpty=" + codeValueEmpty + ", codeIDduplicate=" + codeIDduplicate + ", codeIDFormat=" + codeIDFormat + ", codeValueFormat=" + codeValueFormat + '}';
    }

    public CodeError(String codeIDEmpty, String codeValueEmpty, String codeIDduplicate, String codeIDFormat, String codeValueFormat) {
        this.codeIDEmpty = codeIDEmpty;
        this.codeValueEmpty = codeValueEmpty;
        this.codeIDduplicate = codeIDduplicate;
        this.codeIDFormat = codeIDFormat;
        this.codeValueFormat = codeValueFormat;
    }

    public String getCodeIDFormat() {
        return codeIDFormat;
    }

    public void setCodeIDFormat(String codeIDFormat) {
        this.codeIDFormat = codeIDFormat;
    }

    public String getCodeValueFormat() {
        return codeValueFormat;
    }

    public void setCodeValueFormat(String codeValueFormat) {
        this.codeValueFormat = codeValueFormat;
    }

   

    public String getCodeIDEmpty() {
        return codeIDEmpty;
    }

    public void setCodeIDEmpty(String codeIDEmpty) {
        this.codeIDEmpty = codeIDEmpty;
    }

    public String getCodeValueEmpty() {
        return codeValueEmpty;
    }

    public void setCodeValueEmpty(String codeValueEmpty) {
        this.codeValueEmpty = codeValueEmpty;
    }

    public String getCodeIDduplicate() {
        return codeIDduplicate;
    }

    public void setCodeIDduplicate(String codeIDduplicate) {
        this.codeIDduplicate = codeIDduplicate;
    }

}
