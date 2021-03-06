package org.jkiss.dbeaver.ui.contentassist;

import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyLookupFactory;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.ui.UIUtils;
import org.jkiss.dbeaver.ui.controls.CComboContentAdapter;

public class ContentAssistUtils {

    private static final Log log = Log.getLog(UIUtils.class);

    public static void installContentProposal(Control control, IControlContentAdapter contentAdapter, IContentProposalProvider provider) {
        installContentProposal(control, contentAdapter, provider, true);
    }

    public static ContentProposalAdapter installContentProposal(Control control, IControlContentAdapter contentAdapter, IContentProposalProvider provider, boolean autoActivation) {
        IKeyLookup keyLookup = KeyLookupFactory.getDefault();
        KeyStroke keyStroke = KeyStroke.getInstance(keyLookup.getCtrl(), SWT.SPACE); //$NON-NLS-1$
        final ContentProposalAdapter proposalAdapter = new ContentProposalAdapter(
            control,
            contentAdapter,
            provider,
            keyStroke,
            autoActivation ? ".abcdefghijklmnopqrstuvwxyz_$(".toCharArray() : ".(".toCharArray());
        boolean isSingleValueAdapter = contentAdapter instanceof CComboContentAdapter || contentAdapter instanceof ComboContentAdapter;
        proposalAdapter.setProposalAcceptanceStyle(isSingleValueAdapter ? ContentProposalAdapter.PROPOSAL_REPLACE : ContentProposalAdapter.PROPOSAL_INSERT);
        proposalAdapter.setPopupSize(new Point(300, 200));
        //proposalAdapter.setFilterStyle(ContentProposalAdapter.FILTER_CHARACTER);
        return proposalAdapter;
    }

}
