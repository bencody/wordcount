package at.sitsolutions.wordcount.io.cli;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainArgumentsTest {

    @Test
    public void default_has_no_file_path_and_no_print_index_option() {
        MainArguments arguments = MainArguments.create(new String[]{});

        assertThat(arguments.textInputFilePath).isNull();
        assertThat(arguments.printIndex).isFalse();
    }

    @Test
    public void only_file_path_has_no_print_index_option() {
        MainArguments arguments = MainArguments.create(new String[]{"some/file/path/myfile.txt"});

        assertThat(arguments.textInputFilePath).isEqualTo("some/file/path/myfile.txt");
        assertThat(arguments.printIndex).isFalse();
    }

    @Test
    public void only_print_index_option_has_no_file_path() {
        MainArguments arguments = MainArguments.create(new String[]{"-index"});

        assertThat(arguments.textInputFilePath).isNull();
        assertThat(arguments.printIndex).isTrue();
    }

    @Test
    public void file_path_is_set_to_last_arg_no_matching_anything_else() {
        MainArguments arguments = MainArguments.create(new String[]{"arg1", "-index", "-unknown", "some/file/path/myfile.txt"});

        assertThat(arguments.textInputFilePath).isEqualTo("some/file/path/myfile.txt");
        assertThat(arguments.printIndex).isTrue();
    }
}
