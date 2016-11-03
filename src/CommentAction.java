import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.ui.JBSplitter;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by hanks on 2016/11/2.
 */
public class CommentAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        // TODO: insert action logic here
//        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
//        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
//        PsiFile currentEditorFile = PsiUtilBase.getPsiFileInEditor(editor, project);
//
//        String currentEditorFileName = currentEditorFile.getName();
//        /**
//         * 文件名的前缀
//         */
//        String prefixOfGenerateFileName = currentEditorFileName;
//        System.out.println("prefixOfGenerateFileName = " + prefixOfGenerateFileName);
//        System.out.println(currentEditorFileName);
//
//        injectPanel(project);
//        showHintDialog(currentEditorFile, prefixOfGenerateFileName, project);
    }

    private void injectPanel(Project project) {

//        JPanel container = new JPanel();
//        BorderLayout outerLayout = new BorderLayout();
//        Component layoutComponent = outerLayout.getLayoutComponent(BorderLayout.CENTER);
////        if (layoutComponent instanceof JBSplitter) {
////            JComponent editorComp = ((JBSplitter) layoutComponent).getFirstComponent();
////            editorComp.getLayout()
////        }
////
////        if (layoutComponent is JBSplitter) {
////            // editor is inside firstComponent of a JBSplitter
////            val editorComp = layoutComponent.firstComponent as JPanel
////            layoutComponent = (editorComp.layout as BorderLayout).getLayoutComponent(BorderLayout.CENTER)
////        }
//        JLayeredPane pane = (JLayeredPane) layoutComponent;
//        JPanel panel = (JPanel) pane.getComponent(1);
//        BorderLayout innerLayout = (BorderLayout) panel.getLayout();
//
//
//
//        // Ok we finally found the actual editor layout. Now make sure we haven't already injected into this editor.
//        if (innerLayout.getLayoutComponent(BorderLayout.LINE_END) == null) {
//            JPanel textPanel = new JPanel();
//
//            val glancePanel = new GlancePanel(project, editor, panel, runner)
//            panel.add(glancePanel, BorderLayout.LINE_END)
//            panels.put(editor, glancePanel)
//
//        }

    }


    private void showHintDialog(PsiFile file, String prefix, Project project) {
//        java.util.List<String> codeComment = AnalyzeCode.analyzeCodeComment(file.getText());
//        JLabel label = new JLabel();
//        label.setText(codeComment.toString());
//        label.setVisible(true);
//        Messages.showMessageDialog(codeComment.toString(), file.getName(), Messages.getInformationIcon());
    }
}
