package at.sitsolutions.wordcount.io.cli;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainArgumentsTest {

    @Test
    public void default_has_no_file_path_and_no_print_index_option() {
        MainArguments arguments = MainArguments.create(new String[]{});

        assertThat(arguments.textInputFilePath).isNull();
        assertThat(arguments.dictionaryFilePath).isNull();
        assertThat(arguments.printIndex).isFalse();
    }

    @Test
    public void only_file_path_has_no_print_index_option() {
        MainArguments arguments = MainArguments.create(new String[]{"some/file/path/myfile.txt"});

        assertThat(arguments.textInputFilePath).isEqualTo("some/file/path/myfile.txt");
        assertThat(arguments.dictionaryFilePath).isNull();
        assertThat(arguments.printIndex).isFalse();
    }

    @Test
    public void index_flag_arg_is_supported() {
        MainArguments arguments = MainArguments.create(new String[]{"-index"});

        assertThat(arguments.textInputFilePath).isNull();
        assertThat(arguments.dictionaryFilePath).isNull();
        assertThat(arguments.printIndex).isTrue();
    }

    @Test
    public void dictionary_key_value_arg_is_supported() {
        MainArguments arguments = MainArguments.create(new String[]{"-dictionary=dict.txt"});

        assertThat(arguments.textInputFilePath).isNull();
        assertThat(arguments.dictionaryFilePath).isEqualTo("dict.txt");
        assertThat(arguments.printIndex).isFalse();
    }

    @Test
    public void file_path_is_set_to_last_plain_arg() {
        MainArguments arguments = MainArguments.create(new String[]{"arg1", "-index", "some/file/path/myfile.txt", "-unknown", "-key=value", "-dictionary=dict.txt"});

        assertThat(arguments.textInputFilePath).isEqualTo("some/file/path/myfile.txt");
        assertThat(arguments.dictionaryFilePath).isEqualTo("dict.txt");
        assertThat(arguments.printIndex).isTrue();
    }
}
