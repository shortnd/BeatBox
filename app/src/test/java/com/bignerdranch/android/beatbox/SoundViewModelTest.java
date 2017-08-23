package com.bignerdranch.android.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class SoundViewModelTest {
    // Create an instance of BeatBox
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        // Create a mock BeatBox Object
        // This creates a mocked out version
        // of BeatBox
        mBeatBox = mock(BeatBox.class);
        // Creates a new sound
        mSound = new Sound("assetPath");
        // Creates a SoundViewModel
        mSubject = new SoundViewModel(mBeatBox);
        // Sets the test sound
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        mSubject.onButtonClicked();

        verify(mBeatBox).play(mSound);
    }

}