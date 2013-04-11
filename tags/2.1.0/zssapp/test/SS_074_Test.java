import org.zkoss.ztl.JQuery;
import org.zkoss.ztl.util.ColorVerifingHelper;

//all border
public class SS_074_Test extends SSAbstractTestCase {

    @Override
    protected void executeTest() {
        // Select L13:M14
    	selectCells(11, 12, 12, 13);

    	//click all borders in border toolbar button
    	clickDropdownButtonMenu("$fastIconBtn $borderBtn","All borders");
    	

        // Verify
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", loadCellL13().parent().css("border-right-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", loadCellL13().parent().css("border-bottom-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", loadCellM13().parent().css("border-right-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", loadCellM13().parent().css("border-bottom-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", loadCellL14().parent().css("border-right-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", loadCellL14().parent().css("border-bottom-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", loadCellM14().parent().css("border-right-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", loadCellM14().parent().css("border-bottom-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", getSpecifiedCell(10, 12).parent().css("border-right-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", getSpecifiedCell(10, 13).parent().css("border-right-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", getSpecifiedCell(11, 11).parent().css("border-bottom-color")));
        verifyTrue(ColorVerifingHelper.isEqualColor("#000000", getSpecifiedCell(12, 11).parent().css("border-bottom-color")));
    }
    
    private JQuery loadCellL14() {
        return getSpecifiedCell(11, 13);
    }

    private JQuery loadCellM13() {
        return getSpecifiedCell(12, 12);
    }

    private JQuery loadCellL13() {
        return getSpecifiedCell(11, 12);
    }
    
    private JQuery loadCellM14() {
        return getSpecifiedCell(12, 13);
    }

}
