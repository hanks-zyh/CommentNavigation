import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.PanelWithText;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.ui.JBSplitter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanks on 2016/11/2.
 */
public class EditorPanelInjector implements FileEditorManagerListener {

    private final Project project;
    private Map panels = new HashMap<FileEditor, PanelWithText>();

    public EditorPanelInjector(Project project) {
        this.project = project;
    }

    @Override
    public void fileOpened(@NotNull FileEditorManager fileEditorManager, @NotNull VirtualFile virtualFile) {
        for (FileEditor fileEditor : fileEditorManager.getAllEditors()) {
            inject(fileEditor);
        }
    }

    private void inject(FileEditor editor) {
        if (!(editor instanceof TextEditor)) {
            return;
        }

        try {
            JPanel outerPanel = (JPanel) editor.getComponent();
            BorderLayout outerLayout = (BorderLayout) outerPanel.getLayout();

            Component layoutComponent = outerLayout.getLayoutComponent(BorderLayout.CENTER);

            if (layoutComponent instanceof JBSplitter) {
                // editor is inside firstComponent of a JBSplitter
                JPanel editorComp = (JPanel) ((JBSplitter) layoutComponent).getFirstComponent();
                layoutComponent = ((BorderLayout) editorComp.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            }
            JLayeredPane pane = (JLayeredPane) layoutComponent;
            JPanel panel = (JPanel) pane.getComponent(1);
            BorderLayout innerLayout = (BorderLayout) panel.getLayout();
            // Ok we finally found the actual editor layout. Now make sure we haven't already injected into this editor.
            if (innerLayout.getLayoutComponent(BorderLayout.LINE_END) == null) {
                Editor editor1 = ((TextEditor) editor).getEditor();
                PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(editor1.getDocument());
                java.util.List<Comment> texts = AnalyzeCode.analyzeCodeComment(file.getText());
                if (texts.size() <= 0) {
                    return;
                }
                JPanel textPanel = new JPanel(new GridLayout(texts.size(), 1));
                final String[] comments = new String[texts.size()];
                for (int i = 0; i < texts.size(); i++) {
                    comments[i] = texts.get(i).getComment().replace("#", "  ");
                }
                JList jListFont = new JList(comments);
                jListFont.addListSelectionListener(e -> {
                    int selectedIndex = jListFont.getSelectedIndex();
                    int line = texts.get(selectedIndex).getLine();
                    scrollToLine(editor1, line);
                });
                // 设置jList1对象的带标题边框
                jListFont.setBorder(BorderFactory.createTitledBorder("目录: "));
                // 设置jList1对象的选择模式为单一选择  
                jListFont.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jListFont.setVisibleRowCount(100);
                jListFont.setFixedCellWidth(140);
                jListFont.setFixedCellHeight(30);
                JScrollPane jScrollPane1 = new JScrollPane(jListFont);
                textPanel.add(jScrollPane1);
                textPanel.setVisible(true);
                panel.add(textPanel, BorderLayout.LINE_END);
            } else {
                System.out.println("I07: Injection skipped. Looks like we have already injected something here.");
            }


        } catch (Exception e) {

        }
    }

    private void scrollToLine(Editor editor, int line) {
        System.out.println("scroll: " + line);
        editor.getScrollingModel().disableAnimation();
        editor.getScrollingModel().scrollTo(new LogicalPosition(line, 1), ScrollType.CENTER_UP);
        editor.getScrollingModel().enableAnimation();
    }

    @Override
    public void fileClosed(@NotNull FileEditorManager fileEditorManager, @NotNull VirtualFile virtualFile) {

    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent fileEditorManagerEvent) {

    }
}
