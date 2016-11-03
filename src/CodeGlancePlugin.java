import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Created by hanks on 2016/11/2.
 */
public class CodeGlancePlugin implements ProjectComponent {
    private final Project project;
    private EditorPanelInjector injector;

    public CodeGlancePlugin(Project project) {
        injector = new EditorPanelInjector(project);
        this.project = project;
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
        project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, injector);
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "CodeGlancePlugin";
    }

    @Override
    public void projectOpened() {
        // called when project is opened
    }

    @Override
    public void projectClosed() {
        // called when project is being closed
    }
}
