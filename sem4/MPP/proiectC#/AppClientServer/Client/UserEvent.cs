using System;
namespace Client
{
    public enum UserEvent
    {
        ParticipantAdded
    }

    public class CompetitionUserEvent : EventArgs
    {
        private readonly UserEvent userEvent;
        private readonly Object data;

        public CompetitionUserEvent(UserEvent userEvent, object data)
        {
            this.userEvent = userEvent;
            this.data = data;
        }


        public UserEvent UserEventType { get { return userEvent; } }

        public object Data { get { return data; } }
    }
}