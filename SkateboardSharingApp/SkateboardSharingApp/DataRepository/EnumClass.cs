namespace SkateboardSharingApp.DataRepository
{
    public class EnumClass
    {
        public enum OwnerMessageTypeEnum
        {
            AddNewSaketborad = 1,
            EditExistingSakteboard = 2,
            DeleteExistingSakteboard = 3,
            ToggleSaketboardAvailablity = 4,
            GetSkateboardDetails = 5,
            GetOwnerSkateboards = 6
        }

        public enum BorrowerMessageTypeEnum
        {
            ToggleSaketboardBorrowing = 1,
            GetSkateboardDetails = 2,
            GetAvailableSkateboards = 3

        }
        public enum ActiveSkateboardFilterEnum
        {
            ShowAll = 0,
            ShowActive = 1,
            ShowInActive = 2
        }

        public enum CheckedoutFilerEnum
        {
            ShowAll = 0,
            ShowCheckedout = 1,
            ShowNotCheckedout = 2
        }
    }
}
